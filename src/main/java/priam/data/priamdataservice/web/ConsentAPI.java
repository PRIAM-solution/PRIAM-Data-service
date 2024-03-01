package priam.data.priamdataservice.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.dto.consent.ConsentRequestDTO;
import priam.data.priamdataservice.dto.consent.ConsentResponseDTO;
import priam.data.priamdataservice.services.ConsentServiceInterface;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("processing/consent")
@CrossOrigin(origins = "*", allowedHeaders = "*")
@AllArgsConstructor
public class ConsentAPI {
    private final ConsentServiceInterface consentServiceInterface;

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
     * Save a new Consend
     * @param consentRequestDTO Information of the Consent
     * @return The created Consent object
     */
    @PostMapping("")
    public ConsentResponseDTO createConsent(@RequestBody ConsentRequestDTO consentRequestDTO) {
        return consentServiceInterface.save(consentRequestDTO);
    }
}
