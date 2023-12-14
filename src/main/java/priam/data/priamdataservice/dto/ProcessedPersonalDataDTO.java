package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedPersonalDataDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PrimaryKeysListItem {
        private String primaryKeyName;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataListItem {
        private int dataId;
        private String attributeName;
        private List<String> dataValue;
        private int dataConservationDuration;
        private String source;
        private String sourceDetails;
        private String personalDataCategory;
    }
    private String dataTypeName;
    private List<PrimaryKeysListItem> primaryKeys;
    private List<DataListItem> data;


    public ProcessedPersonalDataDTO(String dataTypeName) {
        this.setDataTypeName(dataTypeName);
        this.setPrimaryKeys(new ArrayList<>());
    }
    public void addPrimaryKey(String primaryKeyName) {
        this.getPrimaryKeys().add(new PrimaryKeysListItem(primaryKeyName));
    }
    public void addData(int dataId, String dataAttributeName, List<String> dataValues, int dataConservationDuration, String source, String sourceDetails, String personalDataCategory) {
        DataListItem data = new DataListItem();
        data.setDataId(dataId);
        data.setAttributeName(dataAttributeName);
        data.setDataValue(dataValues);
        data.setSource(source);
        data.setSourceDetails(sourceDetails);
        data.setDataConservationDuration(dataConservationDuration);
        data.setPersonalDataCategory(personalDataCategory);

        this.getData().add(data);
    }
}
