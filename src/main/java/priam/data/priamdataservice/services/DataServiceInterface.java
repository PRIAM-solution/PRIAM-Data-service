package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.dto.transfer.DataListTransferDTO;
import priam.data.priamdataservice.dto.transfer.SecondaryActorDTO;
import priam.data.priamdataservice.entities.Data;

import java.util.List;

public interface DataServiceInterface {

    DataResponseDTO getData(int id);

    List<DataResponseDTO> findAllPersonalData();

    List<DataResponseDTO> findAllData();

    int getIdByAttribute(String attribute);

    String getAttributeById(int id);

    void setDataAttribute(String attribute, String newValue);

    List<DataResponseDTO> findAllDataByDataSubjectCategory(int dSCategory);
    List<Data> findAllProcessedDataByDataSubjectCategoryAndId(int dSCategory, int dataSubjectId);

    DataResponseDTO save(DataRequestDTO dataRequestDTO);

    DataResponseDTO update(DataRequestDTO dataRequestDTO);

    List<DataResponseDTO> getPersonalDataByDataTypeName(String dataTypeName);

    List<ProcessedPersonalDataDTO> getProcessedPersonalDataList(String idRef);

    List<ProcessedIndirectAndProducedPersonalDataDTO> getProcessedIndirectAndProducedPersonalDataList(String idRef);

    List<DataListTransferDTO> getProcessedPersonalDataListTransfer(String idRef);
}
