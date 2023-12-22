package priam.data.priamdataservice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "gdpr_Datausage")
@lombok.Data @ToString
@NoArgsConstructor
@AllArgsConstructor
public class DataUsage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private boolean personalStatus;

    private boolean c;

    private boolean r;

    private boolean u;

    private boolean d;

    @ToString.Exclude
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Processing processing;
    private int dataId;
    @Transient
    private Data data;

}
