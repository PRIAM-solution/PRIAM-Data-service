package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
@Table(name = "PersonalDataCategory")
public class PersonalDataCategory {
    @Id
    private int pdCategoryId;
    @Column
    private String pdCategoryName;
}
