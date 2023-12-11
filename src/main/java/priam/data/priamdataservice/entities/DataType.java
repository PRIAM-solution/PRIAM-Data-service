package priam.data.priamdataservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JoinColumnOrFormula;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
@Table
public class DataType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JoinColumn(name = "data_type_id")
    private int dataTypeId;

    //@Column(name = "dataTypeName")
    @JoinColumn(name = "data_type_name")
    private String dataTypeName;
    @JoinColumn(name = "primary_key_name")
    private String primaryKeyName;

    @JsonManagedReference
    @OneToMany(mappedBy ="dataType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<Data> dataList;

    //private int dataId;
}
