package priam.data.priamdataservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.DataType;
import java.util.List;

public interface DataRepository extends JpaRepository<Data, Integer> {
    int getIdByAttribute(String attribute);
    Data findByAttribute(String attribute);

    List<Data> findAllByDscId(int dSCategory);
    List<Data> findAllByIsPersonal(boolean isPersonal);
    DataType findDataTypeById(int id);
}
