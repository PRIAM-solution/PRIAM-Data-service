package priam.data.priamdataservice.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.entities.SafeguardType;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class SecondaryActorDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String safeguard;
    private SafeguardType safeguardType;
    private String username;
    private String password;

    private int secondaryActorCategoryId;
    private String secondaryActorCategoryName;
}
