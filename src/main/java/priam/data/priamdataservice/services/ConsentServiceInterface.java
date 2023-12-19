package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.consent.ConsentRequestDTO;
import priam.data.priamdataservice.dto.consent.ConsentResponseDTO;

public interface ConsentServiceInterface {

    ConsentResponseDTO save(ConsentRequestDTO consentRequestDTO);
}
