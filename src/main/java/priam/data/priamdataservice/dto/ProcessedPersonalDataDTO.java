package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessedPersonalDataDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PurposeListItem {
        private String description;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataListItem {
        private String attributeName;
    }
    private String processingName;
    private List<PurposeListItem> purposeList;
    private List<DataListItem> dataList;

    public void addPurposeDescription(String description) {
        this.purposeList.add(new PurposeListItem(description));
    }
    public void addDataAttribute(String description) {
        this.dataList.add(new DataListItem(description));
    }
}
