package priam.data.priamdataservice.repositories.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import priam.data.priamdataservice.dto.transfer.SecondaryActorDTO;
import priam.data.priamdataservice.entities.PersonalDataTransfer;

import java.util.List;

public interface PersonalDataTransferRepository extends JpaRepository<PersonalDataTransfer, Integer> {
    PersonalDataTransfer findPersonalDataTransferByTransferId(int transferId);
    List<SecondaryActorDTO> findSecondaryActorsByDataId(int dataId);
}
