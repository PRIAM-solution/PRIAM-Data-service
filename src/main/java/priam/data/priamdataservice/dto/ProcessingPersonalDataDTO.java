package priam.data.priamdataservice.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProcessingPersonalDataDTO {
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class PurposeListItem {
        private String purposeDescription;
    }
    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    private static class DataListItem {
        private String attributeName;
    }
    private String processingName;
    private List<PurposeListItem> purposes = new ArrayList<>();
    private List<DataListItem> data = new ArrayList<>();

    public void addPurposeDescription(String description) {
        this.purposes.add(new PurposeListItem(description));
    }
    public void addDataAttribute(String description) {
        this.data.add(new DataListItem(description));
    }
}
