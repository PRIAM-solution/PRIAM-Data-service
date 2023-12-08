package priam.data.priamdataservice.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import priam.data.priamdataservice.dto.DataUsageResponseDTO;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.DataUsage;
import priam.data.priamdataservice.mappers.DataUsageMapper;
import priam.data.priamdataservice.openfeign.DataRestClient;
import priam.data.priamdataservice.repositories.DataUsageRepository;

import javax.annotation.Generated;
import javax.transaction.Transactional;
import java.util.Collection;

@Generated(
        value = "org.mapstruct.ap.MappingProcessor",
        date = "2021-05-23T23:03:41+0530"
)

@Slf4j
@Transactional
@Service
public class DataUsageService implements DataUsageServiceInterface {

    private DataUsageRepository dataUsageRepository;
    private DataUsageMapper dataUsageMapper;

    private DataRestClient dataRestClient;

    public DataUsageService(DataUsageMapper dataUsageMapper, DataUsageRepository dataUsageRepository, DataRestClient dataRestClient) {
        this.dataUsageMapper = dataUsageMapper;
        this.dataUsageRepository = dataUsageRepository;
        this.dataRestClient = dataRestClient;
    }
    @Override
    public DataUsage createDataUsage(DataUsage dataUsage) {
        //log.info("CreateDataUsage start Process !");
        DataUsage res = dataUsageRepository.save(dataUsage);
        //log.info("CreateDataUsage end Process !");
        return res;
    }

    @Override
    public DataUsage updateDataUsage(DataUsage dataUsage) {
        //log.info("UpdateDataUsage start Process !");
        DataUsage res = dataUsageRepository.save(dataUsage);
        //log.info("UpdateDataUsage end Process !");
        return res;
    }

    @Override
    public boolean deleteDataUsage(Long dataUsageId) {
        dataUsageRepository.deleteById(dataUsageId);
        return true;
    }

    @Override
    public DataUsageResponseDTO getDataUsage(Long dataUsageId) {
        DataUsage dataUsage = dataUsageRepository.findById(dataUsageId).get();
        Data data = dataRestClient.getData(dataUsage.getDataId());
        dataUsage.setData(data);
        return dataUsageMapper.fromDataUsage(dataUsage);/*.orElseThrow();*/
    }

    @Override
    public Collection<DataUsage> getDataUsages(Long processingId) {
        //Collection<DataUsage> datausages = dataUsageRepository.findAll();
        Collection<DataUsage> datausages = dataUsageRepository.findAllByProcessingId(processingId);
        for (DataUsage d: datausages){
            d.setData(dataRestClient.getData(d.getDataId()));
        }
        return datausages;
    }
}
