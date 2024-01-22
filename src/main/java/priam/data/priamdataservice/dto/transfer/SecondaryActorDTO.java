package priam.data.priamdataservice.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.entities.Country;
import priam.data.priamdataservice.entities.SafeguardType;
import priam.data.priamdataservice.entities.SecondaryActorType;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class SecondaryActorDTO { //TODO: refactor usage
    private int secondaryActorId;
    private SecondaryActorType secondaryActorType;
    private String secondaryActorName;
    private String secondaryActorEmail;
    private String secondaryActorPhone;
    private String secondaryActorAddress;
    private String safeguard;
    private SafeguardType safeguardType;
    private String username;
    private String password;
    private Country country;
    private SecondaryActorCategoryDTO secondaryActorCategory;
}
