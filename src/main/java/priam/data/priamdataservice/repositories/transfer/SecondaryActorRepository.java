package priam.data.priamdataservice.repositories.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import priam.data.priamdataservice.entities.SecondaryActor;

public interface SecondaryActorRepository extends JpaRepository<SecondaryActor, Long> {
    SecondaryActor findSecondaryActorById(int id);
}
