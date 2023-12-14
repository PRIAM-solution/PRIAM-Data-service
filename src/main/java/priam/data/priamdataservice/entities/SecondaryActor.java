package priam.data.priamdataservice.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@lombok.Data
@Table(name = "secondary_actor")
public class SecondaryActor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String safeguard;
    private SafeguardType safeguardType;
    private String username;
    private String password;

    @ManyToOne
    private SecondaryActorCategory secondaryActorCategory;
}
