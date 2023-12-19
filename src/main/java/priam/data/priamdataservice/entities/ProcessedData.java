package priam.data.priamdataservice.entities;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@lombok.Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@IdClass(ProcessedDataKey.class)
@Table(name="processed_data")
public class ProcessedData {
    @Id
    private int dataSubjectId;
    @Id
    private int dataId;
}
