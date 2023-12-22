package priam.data.priamdataservice.web;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import priam.data.priamdataservice.dto.transfer.*;
import priam.data.priamdataservice.services.TransferService;

@RestController
@RequestMapping("transfer")
@AllArgsConstructor
public class TransferAPI {
    private final TransferService service;

    @PostMapping("")
    public PersonalDataTransferDTO createTransfer(@RequestBody PersonalDataTransferDTO transferDTO) {
        return service.createTransfer(transferDTO);
    }

    @PostMapping("/secondaryActor")
    public SecondaryActorDTO createSecondaryActor(@RequestBody SecondaryActorDTO secondaryActorDTO) {
        return service.createSecondaryActor(secondaryActorDTO);
    }
    @PostMapping("/secondaryActorCategory")
    public SecondaryActorCategoryDTO createSecondaryActorCategory(@RequestBody SecondaryActorCategoryDTO secondaryActorCategoryDTO) {
        return service.createSecondaryActorCategory(secondaryActorCategoryDTO);
    }
    @PostMapping("/secondaryActorTransfer")
    public void createSecondaryActorTransfer(@RequestBody SecondaryActorTransferDTO secondaryActorTransferDTO) {
        service.createSecondaryActorTransfer(secondaryActorTransferDTO);
    }
    @PostMapping("/dataTransfer")
    public void createDataTransfer(@RequestBody DataTransferDTO dataTransferDTO) {
        service.createDataTransfer(dataTransferDTO);
    }
}
