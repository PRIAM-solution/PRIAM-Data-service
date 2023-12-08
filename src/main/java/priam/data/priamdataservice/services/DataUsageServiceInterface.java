package priam.data.priamdataservice.services;


import priam.data.priamdataservice.dto.DataUsageResponseDTO;
import priam.data.priamdataservice.entities.DataUsage;

import java.util.Collection;

public interface DataUsageServiceInterface {
    DataUsage createDataUsage(DataUsage dataUsage);
    DataUsage updateDataUsage(DataUsage dataUsage);
    boolean deleteDataUsage(Long dataUsageId);
    DataUsageResponseDTO getDataUsage(Long dataUsageId);
    Collection<DataUsage> getDataUsages(Long processingId);
}
