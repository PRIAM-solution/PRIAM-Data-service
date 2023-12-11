package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.enums.CategoryMesure;
import priam.data.priamdataservice.enums.TypeMesure;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "gdpr_mesure")
public class Mesure {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int mesureID;
    private String description;
    private TypeMesure type;
    private CategoryMesure category;
}
