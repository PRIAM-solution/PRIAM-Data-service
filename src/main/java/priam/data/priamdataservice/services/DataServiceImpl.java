package priam.data.priamdataservice.services;

import org.springframework.stereotype.Service;
import priam.data.priamdataservice.dto.DataRequestDTO;
import priam.data.priamdataservice.dto.DataResponseDTO;
import priam.data.priamdataservice.dto.DataTypeResponseDTO;
import priam.data.priamdataservice.entities.DSCategory;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.DataType;
import priam.data.priamdataservice.mappers.DataMapper;
import priam.data.priamdataservice.mappers.DataTypeMapper;
import priam.data.priamdataservice.openfeign.DataSubjectRestClient;
import priam.data.priamdataservice.repositories.DataRepository;
import priam.data.priamdataservice.repositories.DataTypeRepository;

import javax.annotation.Generated;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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

    final DataTypeRepository dataTypeRepository;
    private DataRequestDTO dataRequestDTO;

    public DataServiceImpl(DataRepository dataRepository, DataMapper dataMapper, DataTypeMapper dataTypeMapper, DataTypeRepository dataTypeRepository, DataSubjectRestClient dataSubjectRestClient) {
        this.dataRepository = dataRepository;
        this.dataMapper = dataMapper;
        this.dataTypeMapper = dataTypeMapper;
        this.dataTypeRepository = dataTypeRepository;
        this.dataSubjectRestClient = dataSubjectRestClient;
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
}
