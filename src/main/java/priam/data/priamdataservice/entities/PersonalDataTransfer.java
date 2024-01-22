package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
@Table(name = "PersonalDataTransfer")
public class PersonalDataTransfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transfer_id")
    private int personalDataTransferId;

    @OneToOne
    private Processing processing;

    @ManyToMany
    @JoinTable(name = "data_transfer",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "data_id"))
    private List<Data> data;

    @ManyToMany
    @JoinTable(name = "secondary_actor_transfer",
            joinColumns = @JoinColumn(name = "transfer_id"),
            inverseJoinColumns = @JoinColumn(name = "actor_id"))
    private List<SecondaryActor> secondaryActors;
}
