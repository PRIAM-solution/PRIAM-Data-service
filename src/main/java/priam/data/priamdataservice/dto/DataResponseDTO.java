package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.entities.DataSubjectCategory;
import priam.data.priamdataservice.entities.PersonalDataCategory;
import priam.data.priamdataservice.enums.Category;
import priam.data.priamdataservice.enums.Source;

@Data @AllArgsConstructor
@NoArgsConstructor
public class DataResponseDTO {
    private int dataId;
    private String dataName;
    private boolean isPersonal;
    private Category category;
    private Source source;
    private int dataConservation;
    private boolean isPortable;
    private int data_type_id;
    private String data_type_name;
    private boolean isPrimaryKey;
    private DataSubjectCategory dataSubjectCategory;
    private PersonalDataCategory personalDataCategory;
}
