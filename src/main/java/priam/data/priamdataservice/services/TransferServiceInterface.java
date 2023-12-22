package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.transfer.*;

public interface TransferServiceInterface {
    PersonalDataTransferDTO createTransfer(PersonalDataTransferDTO transferDTO);
    SecondaryActorDTO createSecondaryActor(SecondaryActorDTO secondaryActorDTO);
    SecondaryActorCategoryDTO createSecondaryActorCategory(SecondaryActorCategoryDTO secondaryActorCategoryDTO);
    void createSecondaryActorTransfer(SecondaryActorTransferDTO secondaryActorTransferDTO);
    void createDataTransfer(DataTransferDTO dataTransferDTO);
}
