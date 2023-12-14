package priam.data.priamdataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priam.data.priamdataservice.entities.Consent;

import java.util.List;

public interface ConsentRepository extends JpaRepository<Consent, Integer> {

    @Query(value = "SELECT data_id FROM consent WHERE processing_id = :processingId",
        nativeQuery = true)
    List<Integer> findDataIdByProcessingId(int processingId);
}
