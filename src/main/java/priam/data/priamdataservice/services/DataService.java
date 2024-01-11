package priam.data.priamdataservice.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.dto.transfer.DataListTransferDTO;
import priam.data.priamdataservice.dto.transfer.SecondaryActorCategoryDTO;
import priam.data.priamdataservice.dto.transfer.SecondaryActorDTO;
import priam.data.priamdataservice.entities.DSCategory;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.SecondaryActor;
import priam.data.priamdataservice.enums.Source;
import priam.data.priamdataservice.mappers.DataMapper;
import priam.data.priamdataservice.mappers.DataTypeMapper;
import priam.data.priamdataservice.mappers.PersonalDataTransferMapper;
import priam.data.priamdataservice.openfeign.ActorRestClient;
import priam.data.priamdataservice.openfeign.ProviderRestClient;
import priam.data.priamdataservice.openfeign.RightRestClient;
import priam.data.priamdataservice.repositories.DataRepository;
import priam.data.priamdataservice.repositories.DataTypeRepository;
import priam.data.priamdataservice.repositories.ProcessedDataRepository;
import priam.data.priamdataservice.repositories.transfer.PersonalDataTransferRepository;
import priam.data.priamdataservice.repositories.transfer.SecondaryActorRepository;

import javax.annotation.Generated;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-05-23T23:03:41+0530"
)
@Service
@Transactional
@AllArgsConstructor
public class DataService implements DataServiceInterface {
    final DataRepository dataRepository;
    final DataMapper dataMapper;

    final DataTypeMapper dataTypeMapper;
    final PersonalDataTransferMapper transferMapper;
    final ActorRestClient actorRestClient;
    final RightRestClient rightRestClient;
    final ProviderRestClient providerRestClient;

    final DataTypeRepository dataTypeRepository;
    final ProcessedDataRepository processedDataRepository;
    final SecondaryActorRepository secondaryActorRepository;
    private final PersonalDataTransferRepository personalDataTransferRepository;

    @Override
    public DataResponseDTO save(DataRequestDTO dataRequestDTO) {
        Data data = dataMapper.DataRequestDTOToData(dataRequestDTO);
        Data saveData = dataRepository.save(data);
        return dataMapper.DataToDataResponseDTO(saveData);
    }

    @Override
    public DataResponseDTO update(DataRequestDTO dataRequestDTO) {
        Data data = dataMapper.DataRequestDTOToData(dataRequestDTO);
        Data updatedData = dataRepository.save(data);
        return dataMapper.DataToDataResponseDTO(updatedData);
    }

    @Override
    public DataResponseDTO getData(int id) {
        Data data = dataRepository.findById(id).get();
        DSCategory dsCategory = actorRestClient.getDSCategoryById(data.getDscId());
        data.setDsCategory(dsCategory);
        DataResponseDTO dataResponseDTO = dataMapper.DataToDataResponseDTO(data);

        return dataResponseDTO;
    }

    @Override
    public List<DataResponseDTO> findAllPersonalData() {
        List<Data> dataList = dataRepository.findAllByIsPersonal(true);
        for (Data datum : dataList) {
            DSCategory dsCategory = actorRestClient.getDSCategoryById(datum.getDscId());
            datum.setDsCategory(dsCategory);
        }
        List<DataResponseDTO> dataResponseDTOS = dataList
                .stream().map(datum -> dataMapper.DataToDataResponseDTO(datum))
                .collect(Collectors.toList());
        return dataResponseDTOS;
    }

    @Override
    public List<DataResponseDTO> findAllData() {
        List<Data> dataList = dataRepository.findAll();
        for (Data datum : dataList) {
            DSCategory dsCategory = actorRestClient.getDSCategoryById(datum.getDscId());
            datum.setDsCategory(dsCategory);
        }
        List<DataResponseDTO> dataResponseDTOS = dataList
                .stream().map(datum -> dataMapper.DataToDataResponseDTO(datum))
                .collect(Collectors.toList());
        return dataResponseDTOS;
    }

    @Override
    public int getIdByAttribute(String attribute) {
        Data d = dataRepository.findByAttribute(attribute);
        return d.getId();
    }

    @Override
    public String getAttributeById(int id) {
        Data d = dataRepository.findById(id).get();
        return d.getAttribute();
    }

    @Override
    public void setDataAttribute(String attribute, String newValue) {
        Data d = new Data();
        d = dataRepository.findById(1).get();
        d.setAttribute(newValue);

    }

    @Override
    public List<DataResponseDTO> findAllDataByDataSubjectCategory(int dSCategory) {
        List<Data> dataList = (List<Data>) dataRepository.findAllByDscId(dSCategory);
        List<DataResponseDTO> personalData = dataList.stream()
                .filter(dto -> dto.isPersonal())
                .map(dto -> dataMapper.DataToDataResponseDTO(dto))
                .collect(Collectors.toList());
        return personalData;
    }

    @Override
    public List<Data> findAllProcessedDataByDataSubjectCategoryAndId(int dSCategory, int dataSubjectId) {
        List<Data> dataList = (List<Data>) dataRepository.findAllByDscId(dSCategory);
        List<Integer> processedDataIds = processedDataRepository.findDataIdByDataSubjectId(dataSubjectId);
        List<Data> personalData = dataList.stream()
                .filter(dto -> dto.isPersonal() && processedDataIds.contains(dto.getId()))
                .collect(Collectors.toList());
        return personalData;
    }

    @Override
    public List<DataResponseDTO> getPersonalDataByDataTypeName(String dataTypeName) {
        List<DataResponseDTO> dataListByDataType = new LinkedList<>();
        List<DataResponseDTO> dataList = findAllPersonalData();
        for (DataResponseDTO datum : dataList) {
            if (datum.getData_type_name().equals(dataTypeName))
                dataListByDataType.add(datum);
            System.out.println(datum.getData_type_name());
        }
        for (DataResponseDTO datum : dataListByDataType) {
            System.out.println(datum.getData_type_name());
        }
        return dataListByDataType;
    }

    @Override
    public List<ProcessedPersonalDataDTO> getProcessedPersonalDataList(String idRef) {
        ArrayList<ProcessedPersonalDataDTO> response = new ArrayList<>();
        DataSubjectResponseDTO dataSubject = actorRestClient.getDataSubjectByRef(idRef);
        int dSCategory = dataSubject.getDscId();
        int dataSubjectId = dataSubject.getId();

        ArrayList<Data> dataList = new ArrayList<>(this.findAllProcessedDataByDataSubjectCategoryAndId(dSCategory, dataSubjectId));

        // First, get all direct datas
        ArrayList<Data> directDatas = new ArrayList<>(dataList.stream().filter(d -> d.getSource().equals(Source.Direct)).toList());

        directDatas.forEach(data -> {
            // Construct each dataType
            Optional<ProcessedPersonalDataDTO> processedPersonalDataDTO = response.stream().filter(p -> p.getDataTypeName().equals(data.getDataType().getDataTypeName())).findFirst();
            ProcessedPersonalDataDTO dataType = null;
            if (processedPersonalDataDTO.isPresent()) {
                dataType = processedPersonalDataDTO.get();
            } else {
                dataType = new ProcessedPersonalDataDTO(data.getDataType().getDataTypeName());
                response.add(dataType);
            }
            // Get data values
            ArrayList<String> attributesNames = new ArrayList<>();
            attributesNames.add(data.getAttribute());
            ArrayList<Map<String, String>> valuesResponse = new ArrayList<>(providerRestClient.getPersonalDataValues(idRef, dataType.getDataTypeName(), attributesNames));
            ArrayList<String> values = new ArrayList<>();
            valuesResponse.forEach(valueMap -> {
                if (valueMap.get("attribute").equals(data.getAttribute()))
                    values.add(valueMap.get("value"));
            });
            dataType.addData(data.getId(), data.getAttribute(), values, data.getDataConservation(), data.getSource().name(), data.getSource().name(), data.getPersonalDataCategory().getPdCategoryName());

            // If the data was a primaryKey of the DataType, we add it to the primaryKey list
            if (data.isPrimaryKey()) {
                dataType.addPrimaryKey(data.getAttribute());
            }
        });

        // Then the same thing, with the accepted undirect and produced datas
        ArrayList<Data> nondirectDatas = new ArrayList<>(dataList.stream().filter(d -> d.getSource().equals(Source.Indirect) || d.getSource().equals(Source.Produced)).toList());
        nondirectDatas.forEach(data -> {
            // We have to verify if provider accepted to give this data
            boolean isAccepted = rightRestClient.getIfDataAccessAccepted(new IsAcceptedDTO(dataSubjectId, data.getId()));
            if (isAccepted) {
                // Construct each dataType
                Optional<ProcessedPersonalDataDTO> processedPersonalDataDTO = response.stream().filter(p -> p.getDataTypeName().equals(data.getDataType().getDataTypeName())).findFirst();
                ProcessedPersonalDataDTO dataType = null;
                if (processedPersonalDataDTO.isPresent()) {
                    dataType = processedPersonalDataDTO.get();
                } else {
                    dataType = new ProcessedPersonalDataDTO(data.getDataType().getDataTypeName());
                    response.add(dataType);
                }
                // Get data values
                ArrayList<String> attributesNames = new ArrayList<>();
                attributesNames.add(data.getAttribute());
                ArrayList<Map<String, String>> valuesResponse = new ArrayList<>(providerRestClient.getPersonalDataValues(idRef, dataType.getDataTypeName(), attributesNames));
                ArrayList<String> values = new ArrayList<>();
                valuesResponse.forEach(valueMap -> {
                    if (valueMap.get("attribute").equals(data.getAttribute()))
                        values.add(valueMap.get("value"));
                });
                dataType.addData(data.getId(), data.getAttribute(), values, data.getDataConservation(), data.getSource().name(), data.getSource().name(), data.getPersonalDataCategory().getPdCategoryName());

                // If the data was a primaryKey of the DataType, we add it to the primaryKey list
                if (data.isPrimaryKey()) {
                    dataType.addPrimaryKey(data.getAttribute());
                }
            }
        });
        return response;
    }

    @Override
    public List<ProcessedIndirectAndProducedPersonalDataDTO> getProcessedIndirectAndProducedPersonalDataList(String idRef) {
        ArrayList<ProcessedIndirectAndProducedPersonalDataDTO> response = new ArrayList<>();
        DataSubjectResponseDTO dataSubject = actorRestClient.getDataSubjectByRef(idRef);
        int dSCategory = dataSubject.getDscId();
        int dataSubjectId = dataSubject.getId();
        ArrayList<Data> dataList = new ArrayList<>(this.findAllProcessedDataByDataSubjectCategoryAndId(dSCategory, dataSubjectId));

        // Get indirect and produced datas
        ArrayList<Data> nondirectDatas = new ArrayList<>(dataList.stream().filter(d -> d.getSource().equals(Source.Indirect) || d.getSource().equals(Source.Produced)).toList());

        nondirectDatas.forEach(data -> {
            // Construct each dataType
            Optional<ProcessedIndirectAndProducedPersonalDataDTO> processedIndirectAndProducedPersonalDataDTO = response.stream().filter(p -> p.getDataTypeName().equals(data.getDataType().getDataTypeName())).findFirst();
            ProcessedIndirectAndProducedPersonalDataDTO dataType = null;
            if (processedIndirectAndProducedPersonalDataDTO.isPresent()) {
                dataType = processedIndirectAndProducedPersonalDataDTO.get();
            } else {
                dataType = new ProcessedIndirectAndProducedPersonalDataDTO(data.getDataType().getDataTypeName());
                response.add(dataType);
            }

            dataType.addData(data.getId(), data.getAttribute());
        });

        return response;
    }

//    @Override
//    public List<DataListTransferDTO> getProcessedPersonalDataListTransfer(String idRef) {
//        // Get the list of processed personal data
//        List<ProcessedPersonalDataDTO> processedPersonalDataDTOList = getProcessedPersonalDataList(idRef);
//
//        List<SecondaryActor> secondaryActorArrayList = new ArrayList<>();
//
//        // Iterate through each processed data
//        for (ProcessedPersonalDataDTO processedPersonalDataDTO : processedPersonalDataDTOList) {
//            // Iterate through data items within the processed data
//            ArrayList<Integer> dataIds = new ArrayList<>();
//            for (ProcessedPersonalDataDTO.DataListItem dataListItem : processedPersonalDataDTO.getData()) {
//                dataIds.add(dataListItem.getDataId());
//            }
//            // Query PersonalDataTransfer repository to get secondary actors by dataId
//            List<SecondaryActor> secondaryActors = secondaryActorRepository.findSecondaryActorByDataIds(dataIds);
//            // Add fetched secondary actors to the result list, if not already present
//            for (SecondaryActor secondaryActor : secondaryActors) {
//                if (!secondaryActorArrayList.contains(secondaryActor)) {
//                    secondaryActorArrayList.add(secondaryActor);
//                }
//            }
//        }
//
//        return secondaryActorArrayList.stream().map(dto -> transferMapper.SecondaryActorToSecondaryActorDTO(dto)).toList();
//    }

    /**
     * Retrieves a list of DataListTransferDTO objects based on the specified reference ID.
     * For each processed personal data associated with the reference ID, fetches secondary actors
     * and constructs DataListTransferDTO objects containing the secondary actors and their
     * corresponding processed personal data.
     *
     * @param idRef The reference ID used to retrieve processed personal data.
     * @return A list of DataListTransferDTO objects containing secondary actors and their
     *         associated processed personal data.
     */
    @Override
    public List<DataListTransferDTO> getProcessedPersonalDataListTransfer(String idRef) {
        // Get the list of processed personal data
        List<ProcessedPersonalDataDTO> processedPersonalDataDTOList = getProcessedPersonalDataList(idRef);
        System.out.println("[1] ProcessedPersonalDataDTOList: " + processedPersonalDataDTOList);

        List<DataListTransferDTO> dataListTransferDTOList = new ArrayList<>();
        Map<Integer, List<SecondaryActor>> secondaryActorMap = new HashMap<>();
        // Iterate through each processed data
        for (ProcessedPersonalDataDTO processedPersonalDataDTO : processedPersonalDataDTOList) {
            // Fetch secondary actors for each data item in processed data
            for (ProcessedPersonalDataDTO.DataListItem dataListItem : processedPersonalDataDTO.getData()) {
                int dataId = dataListItem.getDataId();
                List<SecondaryActor> secondaryActors = secondaryActorRepository.findSecondaryActorByDataId(dataId);
                secondaryActorMap.put(dataId, secondaryActors);
            }
            System.out.println("[2] SecondaryActorMap: " + secondaryActorMap);
        }

        for (Integer dataId : secondaryActorMap.keySet()) {
            List<SecondaryActor> secondaryActors = secondaryActorMap.get(dataId);
            System.out.println("[3] SecondaryActors: " + secondaryActors);
            for (SecondaryActor secondaryActor : secondaryActors) {
                System.out.println("[4] SecondaryActor: " + secondaryActor);
                if (!dataListTransferDTOList.contains(secondaryActor)) {
                    // Construct each dataListTransferDTO and add it to the list
                    DataListTransferDTO dataListTransferDTO = new DataListTransferDTO(
                            secondaryActor.getId(),
                            secondaryActor.getName(),
                            secondaryActor.getEmail(),
                            secondaryActor.getPhone(),
                            secondaryActor.getAddress(),
                            secondaryActor.getCountry(),
                            secondaryActor.getSafeguard(),
                            secondaryActor.getSafeguardType(),
                            secondaryActor.getUsername(),
                            secondaryActor.getPassword(),
                            new SecondaryActorCategoryDTO(
                                    secondaryActor.getSecondaryActorCategory().getSecondaryActorCategoryId(),
                                    secondaryActor.getSecondaryActorCategory().getSecondaryActorCategoryName()),
                            new ProcessedPersonalDataDTO() // Set dataTransfers accordingly
                    );
                    // Retrieve the ProcessedPersonalDataDTO of the processedPersonalDataDTOList with the dataId
                    ProcessedPersonalDataDTO matchingProcessedData = processedPersonalDataDTOList.stream()
                            .filter(data -> data.getData().stream().anyMatch(item -> item.getDataId() == dataId))
                            .findFirst()
                            .orElse(null);
                    System.out.println("[5] matchingProcessedData: " + matchingProcessedData);

                    if (matchingProcessedData != null) {
                        dataListTransferDTO.setDataTransfers(matchingProcessedData);
                        System.out.println("[6] dataTransfersAdded?: " + dataListTransferDTO.getDataTransfers());
                        dataListTransferDTOList.add(dataListTransferDTO);
                        System.out.println("[7] DataListTransferDTO: " + dataListTransferDTOList);
                    }
                }
            }
        }
        System.out.println("[8] DataListTransferDTOList: " + dataListTransferDTOList);
        return dataListTransferDTOList;
    }
}
