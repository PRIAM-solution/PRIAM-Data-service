package priam.data.priamdataservice.repositories.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import priam.data.priamdataservice.dto.transfer.SecondaryActorDTO;
import priam.data.priamdataservice.entities.PersonalDataTransfer;
import priam.data.priamdataservice.entities.SecondaryActor;

import javax.persistence.*;
import java.util.List;

@Repository
public interface PersonalDataTransferRepository extends JpaRepository<PersonalDataTransfer, Integer> {
    PersonalDataTransfer findPersonalDataTransferByTransferId(int transferId);
}
