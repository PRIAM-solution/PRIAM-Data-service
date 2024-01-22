package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.ProcessingPersonalDataDTO;
import priam.data.priamdataservice.dto.ProcessingRequestDTO;
import priam.data.priamdataservice.dto.ProcessingResponseDTO;
import priam.data.priamdataservice.entities.Processing;

import java.util.Collection;

public interface ProcessingServiceInterface {
    Processing createProcessing(ProcessingRequestDTO processingRequestDTO);
    ProcessingResponseDTO updateProcessing(Integer processingId, ProcessingRequestDTO processingRequestDTO);
    boolean deleteProcessing(Integer processingId);
    ProcessingResponseDTO getProcessing(Integer processingId);
    Collection<Processing> getProcessings();

    Collection<ProcessingResponseDTO> getProcessingsByDataSubjectCategoryId(int dataSubjectCategoryId);

    Collection<ProcessingPersonalDataDTO> getProcessingPersonalDataListPurposes(String idRef);

}
