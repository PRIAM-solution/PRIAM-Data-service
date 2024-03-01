package priam.data.priamdataservice.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.entities.DataUsage;
import priam.data.priamdataservice.services.DataUsageServiceInterface;
import priam.data.priamdataservice.dto.DataUsageResponseDTO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collection;

@RestController
@RequestMapping("processing/data-usage")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class DataUsageController {
    @Autowired
    DataUsageServiceInterface dataUsageService;
    @Autowired
    HttpServletRequest request;

    /**
     * Adds the original URL of the request to the session.
     * <p>
     * This method is annotated with {@literal @}ModelAttribute, which ensures
     * that it is invoked before handling any request mapping method in this
     * controller. It sets the 'originalUrl' attribute in the session to the
     * request's URI. This attribute can be used to track the original URL
     * requested by the user before any redirection or processing occurs.
     *
     * @param session the HTTP session to which the original URL attribute is added
     */
    @ModelAttribute
    public void addOriginalUrlToSession(HttpSession session) {
        session.setAttribute("originalUrl", request.getRequestURI());
    }

    /**
     * Save a new DataUsage
     * @param dataUsage Information of the DataUsage
     * @return The created DataUsage object
     */
    @PostMapping("/create")
    public DataUsage newDataUsage(DataUsage dataUsage) {
        return dataUsageService.createDataUsage(dataUsage);
    }

    /**
     * Update a DataUsage
     * @param dataUsage The new information of the DataUsage object
     * @return The updated DataUsage object
     */
    @PutMapping("/update")
    public DataUsage modifyDataUsage(DataUsage dataUsage) {
        return dataUsageService.updateDataUsage(dataUsage);
    }

    /**
     * Retrieve all DataUsage of a processing by the processing ID
     * @param processingId ID of the processing
     * @return A DataUsage object List
     */
    @GetMapping("/")
    public Collection<DataUsage> getDataUsages(int processingId) {
        return dataUsageService.getDataUsages(processingId);
    }

    /**
     * Retrieve a DataUsage object by its ID
     * @param dataUsageId ID of the DataUsage
     * @return A DataUsage object
     */
    @GetMapping("/{dataUsageId}")
    public DataUsageResponseDTO getDataUsage(@PathVariable Integer dataUsageId) {
        return dataUsageService.getDataUsage(dataUsageId);
    }

}