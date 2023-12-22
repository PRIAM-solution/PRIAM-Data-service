package priam.data.priamdataservice.web;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priam.data.priamdataservice.dto.consent.ConsentRequestDTO;
import priam.data.priamdataservice.dto.consent.ConsentResponseDTO;
import priam.data.priamdataservice.services.ConsentServiceInterface;

@RestController
@RequestMapping("processing/consent")
@AllArgsConstructor
public class ConsentAPI {
    private final ConsentServiceInterface service;

    @PostMapping("")
    public ConsentResponseDTO createConsent(@RequestBody ConsentRequestDTO consentRequestDTO) {
        return service.save(consentRequestDTO);
    }
}