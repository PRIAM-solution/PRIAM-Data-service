package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTypeResponseDTO {
    private int dataTypeId;
    private int dataTypeName;
    private List<DataResponseDTO> dataList;
    private String primaryKeyName;
}
