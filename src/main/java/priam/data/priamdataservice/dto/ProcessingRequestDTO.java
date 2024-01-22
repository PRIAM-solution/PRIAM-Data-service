package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import priam.data.priamdataservice.entities.DataUsage;
import priam.data.priamdataservice.entities.Measure;
import priam.data.priamdataservice.entities.Purpose;
import priam.data.priamdataservice.enums.ProcessingCategory;
import priam.data.priamdataservice.enums.ProcessingType;

import java.util.Date;
import java.util.List;
@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingRequestDTO {
    private Long id;
    private String name;
    private ProcessingType type;
    private ProcessingCategory category;
    private Date creationDate;
    private Date updatingDate;
    private List<DataUsage> dataUsages;
    private List<Purpose> purposes;
    private List<Measure> measures;
}
