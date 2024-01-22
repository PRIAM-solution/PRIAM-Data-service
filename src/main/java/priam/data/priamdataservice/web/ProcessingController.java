package priam.data.priamdataservice.web;

import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.dto.ProcessingRequestDTO;
import priam.data.priamdataservice.dto.ProcessingResponseDTO;
import priam.data.priamdataservice.services.ProcessingService;
import priam.data.priamdataservice.entities.Processing;

import java.util.Collection;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/processing", produces = "application/json")
public class ProcessingController {


    private final ProcessingService processingService;

    public ProcessingController(ProcessingService processingService) {
        this.processingService = processingService;
    }

    @PostMapping("/create")
    public Processing newProcessing(@RequestBody ProcessingRequestDTO processingRequestDTO) {
        return processingService.createProcessing(processingRequestDTO);
    }

    @PutMapping("/update/{processingId}")
    public ProcessingResponseDTO modifyProcessing(@PathVariable Long processingId, @RequestBody ProcessingRequestDTO processingRequestDTO) {
        return processingService.updateProcessing(processingId,processingRequestDTO);
    }

    @GetMapping("/{id}")
    public ProcessingResponseDTO getProcessing(@PathVariable Long id) {
        return processingService.getProcessing(id);
    }

    @GetMapping("/listProcessings")
    public Collection<Processing> getProcessings() {
        return processingService.getProcessings();
    }

    @GetMapping("/listProcessings/{dataSubjectCategoryId}")
    public Collection<ProcessingResponseDTO> getProcessingsByDataSubjectCategoryId(@PathVariable int dataSubjectCategoryId) {
        return processingService.getProcessingsByDataSubjectCategoryId(dataSubjectCategoryId);
    }
}