package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
@Table(name = "SecondaryActor")
public class SecondaryActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int secondaryActorId;
    private SecondaryActorType secondaryActorType;
    private String secondaryActorName;
    private String secondaryActorAddress;
    private String secondaryActorPhone;
    private String secondaryActorEmail;
    private String safeguard;
    private SafeguardType safeguardType;

    @ManyToOne
    private Country country;

    private String username;
    private String password;

    @ManyToOne
    private SecondaryActorCategory secondaryActorCategory;
}
