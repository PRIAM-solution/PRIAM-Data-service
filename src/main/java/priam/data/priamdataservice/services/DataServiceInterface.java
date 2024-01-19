package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.dto.transfer.DataListTransferDTO;
import priam.data.priamdataservice.entities.Data;

import java.util.List;

public interface DataServiceInterface {

    DataResponseDTO getData(int id);

    List<DataResponseDTO> findAllPersonalData();

    List<DataResponseDTO> findAllData();

    int getIdByDataName(String dataName);

    String getDataNameById(int id);

    List<DataResponseDTO> findAllDataByDataSubjectCategory(int dSCategory);
    List<Data> findAllProcessedDataByDataSubjectCategoryAndId(int dSCategory, int dataSubjectId);

    DataResponseDTO save(DataRequestDTO dataRequestDTO);

    DataResponseDTO update(DataRequestDTO dataRequestDTO);

    List<DataResponseDTO> getPersonalDataByDataTypeName(String dataTypeName);

    List<ProcessedPersonalDataDTO> getProcessedPersonalDataList(String idRef);

    List<ProcessedIndirectAndProducedPersonalDataDTO> getProcessedIndirectAndProducedPersonalDataList(String idRef);

    List<DataListTransferDTO> getProcessedPersonalDataListTransfer(String idRef);
}
