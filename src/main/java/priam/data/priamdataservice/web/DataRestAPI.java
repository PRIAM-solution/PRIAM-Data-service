package priam.data.priamdataservice.web;

import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.services.DataService;
import priam.data.priamdataservice.services.ProcessingService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api")
public class DataRestAPI {

    private final DataService dataService;
    private final ProcessingService processingService;

    public DataRestAPI(DataService dataService, ProcessingService processingService) {
        this.dataService = dataService;
        this.processingService = processingService;
    }
    @PostMapping(path = "/data")
    public void save(DataRequestDTO dataRequestDTO) {this.dataService.save(dataRequestDTO);}

    @GetMapping(path = "/dataId/{attribute}")
    public int getIdByName(@PathVariable String attribute) {
        return dataService.getIdByAttribute(attribute);
    }

    @GetMapping(path = "/getAttribute/{id}")
    public String getAttributeName(@PathVariable int id) {
        return dataService.getAttributeById(id);
    }

    @GetMapping(path = "/personalDataList")
    public List<DataResponseDTO> personalDataList() {
        return dataService.findAllPersonalData();
    }

    @GetMapping(path = "/personalData/{id}")
    public DataResponseDTO getPersonalData(@PathVariable int id) {
        return dataService.getData(id);
    }

    @GetMapping(path = "/dataList")
    public List<DataResponseDTO> dataList() {
        return dataService.findAllData();
    }

    @GetMapping(path = "/data/{dscategory}")
    public List<DataResponseDTO> getPersonalDataByDSCategory(@PathVariable int dscategory) {
        return dataService.findAllDataByDataSubjectCategory(dscategory);
    }

    @GetMapping(path = "/data/dataType:{dataTypeName}")
    public List<DataResponseDTO> getPersonalDataByDataType(@PathVariable String dataTypeName) {
        return dataService.getPersonalDataByDataTypeName(dataTypeName);
    }

    @GetMapping(path = "/data/processedPersonalDataList/{idRef}")
    public List<ProcessedPersonalDataDTO> getProcessedPersonalDataList(@PathVariable String idRef) {
        return dataService.getProcessedPersonalDataList(idRef);
    }

    @GetMapping(path = "/data/processedPersonalDataList/purposes/{idRef}")
    public List<ProcessingPersonalDataDTO> getProcessingPersonalDataListPurposes(@PathVariable String idRef) {
        return processingService.getProcessingPersonalDataListPurposes(idRef);
    }


}
