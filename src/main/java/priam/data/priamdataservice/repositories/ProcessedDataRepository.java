package priam.data.priamdataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import priam.data.priamdataservice.entities.ProcessedData;

import java.util.List;

public interface ProcessedDataRepository extends JpaRepository<ProcessedData, Integer> {
    @Query(value = "SELECT data_id FROM processed_data WHERE data_subject_id = :dataSubjectId",
        nativeQuery = true)
    List<Integer> findDataIdByDataSubjectId(int dataSubjectId);
}
