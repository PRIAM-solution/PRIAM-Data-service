package priam.data.priamdataservice.services;

import org.springframework.stereotype.Service;
import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.entities.DSCategory;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.DataType;
import priam.data.priamdataservice.enums.Source;
import priam.data.priamdataservice.mappers.DataMapper;
import priam.data.priamdataservice.mappers.DataTypeMapper;
import priam.data.priamdataservice.openfeign.DataSubjectRestClient;
import priam.data.priamdataservice.openfeign.ProviderRestClient;
import priam.data.priamdataservice.openfeign.RightRestClient;
import priam.data.priamdataservice.repositories.DataRepository;
import priam.data.priamdataservice.repositories.DataTypeRepository;

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
public class DataServiceImpl implements DataService {
    final DataRepository dataRepository;
    final DataMapper dataMapper;

    final DataTypeMapper dataTypeMapper;
    final DataSubjectRestClient dataSubjectRestClient;
    final RightRestClient rightRestClient;
    final ProviderRestClient providerRestClient;

    final DataTypeRepository dataTypeRepository;
    private DataRequestDTO dataRequestDTO;

    public DataServiceImpl(DataRepository dataRepository, DataMapper dataMapper, DataTypeMapper dataTypeMapper, DataTypeRepository dataTypeRepository, DataSubjectRestClient dataSubjectRestClient, RightRestClient rightRestClient, ProviderRestClient providerRestClient) {
        this.dataRepository = dataRepository;
        this.dataMapper = dataMapper;
        this.dataTypeMapper = dataTypeMapper;
        this.dataTypeRepository = dataTypeRepository;
        this.dataSubjectRestClient = dataSubjectRestClient;
        this.rightRestClient = rightRestClient;
        this.providerRestClient = providerRestClient;
    }


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
        DSCategory dsCategory = dataSubjectRestClient.getDSCategoryById(data.getDscId());
        data.setDsCategory(dsCategory);
        DataResponseDTO dataResponseDTO = dataMapper.DataToDataResponseDTO(data);

        return dataResponseDTO;
    }
    @Override
    public List<DataResponseDTO> findAllPersonalData() {
        List<Data> dataList = dataRepository.findAllByIsPersonal(true);
        for (Data datum: dataList){
            DSCategory dsCategory = dataSubjectRestClient.getDSCategoryById(datum.getDscId());
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
        for (Data datum: dataList){
            DSCategory dsCategory = dataSubjectRestClient.getDSCategoryById(datum.getDscId());
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
                .map(datum -> dataMapper.DataToDataResponseDTO(datum))
                .filter(dto -> dto.isPersonal())
                .collect(Collectors.toList());
        return personalData;
    }

    @Override
    public List<DataResponseDTO> getPersonalDataByDataTypeName(String dataTypeName) {
        List<DataResponseDTO> dataListByDataType = new LinkedList<>();
        List<DataResponseDTO> dataList = findAllPersonalData();
        for (DataResponseDTO datum: dataList){
            if(datum.getData_type_name().equals(dataTypeName))
                dataListByDataType.add(datum);
                 System.out.println(datum.getData_type_name());
        }
        for (DataResponseDTO datum: dataListByDataType){
            System.out.println(datum.getData_type_name());
        }
        return dataListByDataType;
    }

    @Override
    public List<ProcessedPersonalDataDTO> getProcessedPersonalDataList(String idRef) {
        int dSCategory = dataSubjectRestClient.getDataSubjectByRef(idRef).getDscId();
        ArrayList<Data> dataList = new ArrayList<>(dataRepository.findAllByDscId(dSCategory));
        ArrayList<ProcessedPersonalDataDTO> response = new ArrayList<>();

        // First, get all direct datas
        ArrayList<Data> directDatas = new ArrayList<>(dataList.stream().filter(d -> d.getSource().equals(Source.Direct)).toList());

        directDatas.forEach(data -> {
            // Construct each dataType
            Optional<ProcessedPersonalDataDTO> processedPersonalDataDTO = response.stream().filter(p -> p.getDataTypeName().equals(data.getDataType().getDataTypeName())).findFirst();
            ProcessedPersonalDataDTO dataType = null;
            if(processedPersonalDataDTO.isPresent()) {
                dataType = processedPersonalDataDTO.get();
            }
            else {
                dataType = new ProcessedPersonalDataDTO(data.getDataType().getDataTypeName());
                response.add(dataType);
            }
            // Get data values
            ArrayList<String> attributesNames = new ArrayList<>();
            attributesNames.add(data.getAttribute());
            ArrayList<Map<String, String>> valuesResponse = new ArrayList<>(providerRestClient.getPersonalDataValues(idRef, dataType.getDataTypeName(), attributesNames));
            ArrayList<String> values = new ArrayList<>();
            valuesResponse.forEach(valueMap -> {
                values.add(valueMap.get(data.getAttribute()));
            });
            dataType.addData(data.getAttribute(), values, data.getDataConservation(), data.getSource().name(), data.getSource().name(), data.getPersonalDataCategory().getPdCategoryName());

            // If the data was a primaryKey of the DataType, we add it to the primaryKey list
            if(data.isPrimaryKey()) {
                dataType.addPrimaryKey(data.getAttribute());
            }
        });

        // Then the same thing, with the accepted undirect and produced datas
        int dataSubjectId = dataSubjectRestClient.getDataSubjectByRef(idRef).getId();
        ArrayList<Data> nondirectDatas = new ArrayList<>(dataList.stream().filter(d -> d.getSource().equals(Source.Indirect) || d.getSource().equals(Source.Produced)).toList());
        nondirectDatas.forEach(data -> {
            // We have to verify if provider accepted to give this data
            boolean isAccepted = rightRestClient.getIfDataAccessAccepted(new IsAcceptedDTO(dataSubjectId, data.getId()));
            if(isAccepted) {
                // Construct each dataType
                Optional<ProcessedPersonalDataDTO> processedPersonalDataDTO = response.stream().filter(p -> p.getDataTypeName().equals(data.getDataType().getDataTypeName())).findFirst();
                ProcessedPersonalDataDTO dataType = null;
                if(processedPersonalDataDTO.isPresent()) {
                    dataType = processedPersonalDataDTO.get();
                }
                else {
                    dataType = new ProcessedPersonalDataDTO(data.getDataType().getDataTypeName());
                    response.add(dataType);
                }
                // Get data values
                ArrayList<String> attributesNames = new ArrayList<>();
                attributesNames.add(data.getAttribute());
                ArrayList<Map<String, String>> valuesResponse = new ArrayList<>(providerRestClient.getPersonalDataValues(idRef, dataType.getDataTypeName(), attributesNames));
                ArrayList<String> values = new ArrayList<>();
                valuesResponse.forEach(valueMap -> {
                    values.add(valueMap.get(data.getAttribute()));
                });
                dataType.addData(data.getAttribute(), values, data.getDataConservation(), data.getSource().name(), data.getSource().name(), data.getPersonalDataCategory().getPdCategoryName());

                // If the data was a primaryKey of the DataType, we add it to the primaryKey list
                if(data.isPrimaryKey()) {
                    dataType.addPrimaryKey(data.getAttribute());
                }
            }
        });
        return response;
    }

    @Override
    public List<ProcessedIndirectAndProducedPersonalDataDTO> getProcessedIndirectAndProducedPersonalDataList(String idRef) {
        int dSCategory = dataSubjectRestClient.getDataSubjectByRef(idRef).getDscId();
        ArrayList<Data> dataList = new ArrayList<>(dataRepository.findAllByDscId(dSCategory));
        ArrayList<ProcessedIndirectAndProducedPersonalDataDTO> response = new ArrayList<>();

        // Get indirect and produced datas
        ArrayList<Data> nondirectDatas = new ArrayList<>(dataList.stream().filter(d -> d.getSource().equals(Source.Direct)).toList());

        nondirectDatas.forEach(data -> {
            // Construct each dataType
            Optional<ProcessedIndirectAndProducedPersonalDataDTO> processedIndirectAndProducedPersonalDataDTO = response.stream().filter(p -> p.getDataTypeName().equals(data.getDataType().getDataTypeName())).findFirst();
            ProcessedIndirectAndProducedPersonalDataDTO dataType = null;
            if(processedIndirectAndProducedPersonalDataDTO.isPresent()) {
                dataType = processedIndirectAndProducedPersonalDataDTO.get();
            }
            else {
                dataType = new ProcessedIndirectAndProducedPersonalDataDTO(data.getDataType().getDataTypeName());
                response.add(dataType);
            }

            dataType.addData(data.getId(), data.getAttribute());
        });

        return response;
    }
}
