package priam.data.priamdataservice.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.dto.transfer.*;
import priam.data.priamdataservice.services.TransferService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("transfer")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class TransferAPI {
    private final TransferService transferService;

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
     * Save a new Transfer
     *
     * @param transferDTO Information of the Transfer
     * @return The created Transfer object
     */
    @PostMapping("")
    public PersonalDataTransferDTO createTransfer(@RequestBody PersonalDataTransferDTO transferDTO) {
        return transferService.createTransfer(transferDTO);
    }

    /**
     * Save a new SecondaryActor
     *
     * @param secondaryActorDTO Information of the SecondaryActor
     * @return The created SecondaryActor object
     */
    @PostMapping("/secondaryActor")
    public SecondaryActorDTO createSecondaryActor(@RequestBody SecondaryActorDTO secondaryActorDTO) {
        return transferService.createSecondaryActor(secondaryActorDTO);
    }

    /**
     * Save a new SecondaryActorCategory
     *
     * @param secondaryActorCategoryDTO Information of the SecondaryActorCategory
     * @return The created SecondaryActorCategory object
     */
    @PostMapping("/secondaryActorCategory")
    public SecondaryActorCategoryDTO createSecondaryActorCategory(@RequestBody SecondaryActorCategoryDTO secondaryActorCategoryDTO) {
        return transferService.createSecondaryActorCategory(secondaryActorCategoryDTO);
    }

    /**
     * Save a new SecondaryActorTransfer
     *
     * @param secondaryActorTransferDTO Information of the SecondaryActorTransfer
     */
    @PostMapping("/secondaryActorTransfer")
    public void createSecondaryActorTransfer(@RequestBody SecondaryActorTransferDTO secondaryActorTransferDTO) {
        transferService.createSecondaryActorTransfer(secondaryActorTransferDTO);
    }

    /**
     * Save a new DataTransfer
     *
     * @param dataTransferDTO Information of the DataTransfer
     */
    @PostMapping("/dataTransfer")
    public void createDataTransfer(@RequestBody DataTransferDTO dataTransferDTO) {
        transferService.createDataTransfer(dataTransferDTO);
    }
}
