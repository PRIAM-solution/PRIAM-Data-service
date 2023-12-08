package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.enums.PurposeType;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gdpr_purpose")
public class Purpose {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int purposeID;
    @Column(nullable = false)
    private String description;
    private PurposeType type;
//	@JsonIgnore
//	@ManyToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//	private Processing processing;
}