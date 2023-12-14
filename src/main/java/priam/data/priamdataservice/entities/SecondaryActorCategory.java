package priam.data.priamdataservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
public class SecondaryActorCategory {
    @Id
    private int secondaryActorCategoryId;

    private String secondaryActorCategoryName;

    @JsonManagedReference
    @OneToMany(mappedBy ="secondaryActorCategory", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Collection<DataSubject> secondaryActors;
}
