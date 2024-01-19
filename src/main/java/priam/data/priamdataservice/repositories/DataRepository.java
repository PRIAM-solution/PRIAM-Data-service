package priam.data.priamdataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.DataType;
import java.util.List;
import java.util.Optional;

public interface DataRepository extends JpaRepository<Data, Integer> {
    Optional<Data> findByDataId(int dataId);
    int getIdByAttributeName(String attribute);
    Data findByAttributeName(String attribute);

    List<Data> findAllByDscId(int dSCategory);
    List<Data> findAllByIsPersonal(boolean isPersonal);
    DataType findDataTypeByDataId(int id);
}
