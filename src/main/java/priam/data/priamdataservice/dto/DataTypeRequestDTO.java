package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataTypeRequestDTO {
    private int dataTypeId;
    private int dataTypeName;
    private String primaryKeyName;;
    //private List<DataRequestDTO> dataList;
}
