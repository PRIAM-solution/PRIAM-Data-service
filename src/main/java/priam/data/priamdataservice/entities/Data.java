package priam.data.priamdataservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;
import priam.data.priamdataservice.enums.Category;
import priam.data.priamdataservice.enums.Source;

import javax.persistence.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static ch.qos.logback.core.joran.util.beans.BeanUtil.getPropertyName;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data @ToString
@Table(name = "data")
public class Data {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column
    private String attribute;
    @Column
    private boolean isPersonal;

    @Column
    private Category Category;
    @Column
    private Source source;
    @Column
    private int dataConservation;
    @Column
    private boolean isPortable;
    @Column
    private boolean isPrimaryKey;

    private int dscId;
    @Transient
    private DSCategory dsCategory;
    @Transient
    private DataType dataTypeObject;

    @ToString.Exclude
    @JsonBackReference(value = "data_list")
    @ManyToOne
    @JoinColumn(name = "data_type_id")
    private DataType dataType;

    @JsonBackReference(value = "personal_data_category")
    @ManyToOne
    @JoinColumn(name = "pd_category_id")
    private PersonalDataCategory personalDataCategory;
}
