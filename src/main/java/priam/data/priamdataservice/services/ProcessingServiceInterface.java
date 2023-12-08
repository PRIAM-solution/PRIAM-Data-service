package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.ProcessingRequestDTO;
import priam.data.priamdataservice.dto.ProcessingResponseDTO;
import priam.data.priamdataservice.entities.Processing;

import java.util.Collection;

public interface ProcessingServiceInterface {
    Processing createProcessing(ProcessingRequestDTO processingRequestDTO);
    ProcessingResponseDTO updateProcessing(Long processingID, ProcessingRequestDTO processingRequestDTO);
    boolean deleteProcessing(Long processingId);
    ProcessingResponseDTO getProcessing(Long processingId);
    Collection<Processing> getProcessings();

    Collection<ProcessingResponseDTO> getProcessingsByDsc(int dscId);

}
