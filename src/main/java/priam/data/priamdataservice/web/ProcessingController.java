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

    /**
     * Save a new Processing
     * @param processingRequestDTO Information of the Processing
     * @return The created Processing object
     */
    @PostMapping("/create")
    public Processing newProcessing(@RequestBody ProcessingRequestDTO processingRequestDTO) {
        return processingService.createProcessing(processingRequestDTO);
    }

    /**
     * Update a Processing object
     * @param processingId ID of the Processing
     * @param processingRequestDTO The new information of the Processing
     * @return The updated Processing object
     */
    @PutMapping("/update/{processingId}")
    public ProcessingResponseDTO modifyProcessing(@PathVariable Integer processingId, @RequestBody ProcessingRequestDTO processingRequestDTO) {
        return processingService.updateProcessing(processingId,processingRequestDTO);
    }

    /**
     * Retrieve a Processing object by its ID
     * @param id ID of the Processing
     * @return A Processing object
     */
    @GetMapping("/{id}")
    public ProcessingResponseDTO getProcessing(@PathVariable Integer id) {
        return processingService.getProcessing(id);
    }

    /**
     * Retrieve all Processing
     * @return A Processing object List
     */
    @GetMapping("/listProcessings")
    public Collection<Processing> getProcessings() {
        return processingService.getProcessings();
    }

    /**
     * Retrieve a list of Processing object by the DataSubjectCategoryID
     * @param dataSubjectCategoryId ID of the DataSubjectCategory
     * @return A ProcessingResponseDTO object List
     */
    @GetMapping("/listProcessings/{dataSubjectCategoryId}")
    public Collection<ProcessingResponseDTO> getProcessingsByDataSubjectCategoryId(@PathVariable int dataSubjectCategoryId) {
        return processingService.getProcessingsByDataSubjectCategoryId(dataSubjectCategoryId);
    }
}