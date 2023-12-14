package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.DataRequestDTO;
import priam.data.priamdataservice.dto.DataResponseDTO;
import priam.data.priamdataservice.dto.ProcessedIndirectAndProducedPersonalDataDTO;
import priam.data.priamdataservice.dto.ProcessedPersonalDataDTO;

import java.util.List;

public interface DataService {

    DataResponseDTO getData(int id);

    List<DataResponseDTO> findAllPersonalData();

    List<DataResponseDTO> findAllData();

    int getIdByAttribute(String attribute);

    String getAttributeById(int id);

    void setDataAttribute(String attribute, String newValue);

    List<DataResponseDTO> findAllDataByDataSubjectCategory(int dSCategory);
    List<DataResponseDTO> findAllProcessedDataByDataSubjectCategory(int dSCategory, int dataSubjectId);

    DataResponseDTO save(DataRequestDTO dataRequestDTO);

    DataResponseDTO update(DataRequestDTO dataRequestDTO);

    List<DataResponseDTO> getPersonalDataByDataTypeName(String dataTypeName);

    List<ProcessedPersonalDataDTO> getProcessedPersonalDataList(String idRef);
    List<ProcessedIndirectAndProducedPersonalDataDTO> getProcessedIndirectAndProducedPersonalDataList(String idRef);
}
