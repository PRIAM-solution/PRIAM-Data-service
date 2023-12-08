package priam.data.priamdataservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class DSCategory {
    /*@Id
    private String locationId;*/
    private int dscId;
    private String dscName;
    //String est tamporaire
    private String locationId;
}

