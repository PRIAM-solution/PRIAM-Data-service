package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.entities.DataType;
import priam.data.priamdataservice.entities.PersonalDataCategory;
import priam.data.priamdataservice.enums.Category;
import priam.data.priamdataservice.enums.Source;

import javax.persistence.Column;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataRequestDTO {
    private int id;
    private String attribute;
    private boolean isPersonal;
    private Category category;
    private Source source;
    private int dataConservation;
    private boolean isPortable;
    private boolean isPrimaryKey;
    private int data_type_id;
    private int dscId;
    private PersonalDataCategory personalDataCategory;

}
