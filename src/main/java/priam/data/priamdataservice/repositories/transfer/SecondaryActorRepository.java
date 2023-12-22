package priam.data.priamdataservice.repositories.transfer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priam.data.priamdataservice.entities.SecondaryActor;

import java.util.List;

public interface SecondaryActorRepository extends JpaRepository<SecondaryActor, Long> {
    SecondaryActor findSecondaryActorById(int id);
        @Query(value = "SELECT sa.id, sa.name, sa.email, sa.phone, sa.address, sa.country, sa.safeguard, sa.safeguard_type, sa.username, sa.password,sa.secondary_actor_category_secondary_actor_category_id, sac.secondary_actor_category_name FROM secondary_actor sa INNER JOIN secondary_actor_transfer sat ON sa.id = sat.actor_id " +
            "INNER JOIN personal_data_transfer pdt ON sat.transfer_id = pdt.transfer_id " +
            "INNER JOIN data_transfer dt ON pdt.transfer_id = dt.transfer_id " +
            "INNER JOIN secondary_actor_category sac ON sa.secondary_actor_category_secondary_actor_category_id = sac.secondary_actor_category_id " +
            "WHERE dt.data_id IN :dataIds",
            nativeQuery = true)

    List<SecondaryActor> findSecondaryActorByDataIds(List<Integer> dataIds);
}
