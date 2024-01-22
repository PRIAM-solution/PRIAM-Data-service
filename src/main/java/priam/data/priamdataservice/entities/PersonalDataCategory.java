package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
@Table(name = "PersonalDataCategory")
public class PersonalDataCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int personalDataCategoryId;
    @Column
    private String personalDataCategoryName;
}
