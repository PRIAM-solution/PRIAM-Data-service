package priam.data.priamdataservice.dto.transfer;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.dto.ProcessedPersonalDataDTO;
import priam.data.priamdataservice.entities.SafeguardType;

import java.util.ArrayList;

@AllArgsConstructor
@NoArgsConstructor
@lombok.Data
public class DataListTransferDTO {
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private String country;
    private String safeguard;
    private SafeguardType safeguardType;
    private String username;
    private String password;

    private SecondaryActorCategoryDTO secondaryActorCategory;

    private ProcessedPersonalDataDTO dataTransfers;
}