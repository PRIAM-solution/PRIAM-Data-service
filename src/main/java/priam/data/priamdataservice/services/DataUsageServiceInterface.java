package priam.data.priamdataservice.services;


import priam.data.priamdataservice.dto.DataUsageResponseDTO;
import priam.data.priamdataservice.entities.DataUsage;

import java.util.Collection;

public interface DataUsageServiceInterface {
    DataUsage createDataUsage(DataUsage dataUsage);
    DataUsage updateDataUsage(DataUsage dataUsage);
    boolean deleteDataUsage(Integer dataUsageId);
    DataUsageResponseDTO getDataUsage(Integer dataUsageId);
    Collection<DataUsage> getDataUsages(int processingId);
}
