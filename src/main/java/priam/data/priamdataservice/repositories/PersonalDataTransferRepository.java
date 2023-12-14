package priam.data.priamdataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import priam.data.priamdataservice.dto.SecondaryActorDTO;
import priam.data.priamdataservice.entities.PersonalDataTransfer;

import java.util.List;

@Repository
public interface PersonalDataTransferRepository extends JpaRepository<PersonalDataTransfer, Long> {

    //TODO: check if this works, or jsut do List<PersonalDataTransfer> findByDataId(int dataId); then a new service to convert to DTO
    List<SecondaryActorDTO> findSecondaryActorsByDataId(int dataId);
}
