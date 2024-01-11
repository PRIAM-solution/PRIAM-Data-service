package priam.data.priamdataservice.web;

import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.dto.*;
import priam.data.priamdataservice.dto.transfer.DataListTransferDTO;
import priam.data.priamdataservice.dto.transfer.DataTransferDTO;
import priam.data.priamdataservice.dto.transfer.SecondaryActorDTO;
import priam.data.priamdataservice.services.DataServiceInterface;
import priam.data.priamdataservice.services.DataTypeServiceInterface;
import priam.data.priamdataservice.services.ProcessingServiceInterface;

import java.util.Collection;
import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api")
public class DataRestAPI {

    private final DataServiceInterface dataService;
    private final DataTypeServiceInterface dataTypeService;
    private final ProcessingServiceInterface processingService;

    public DataRestAPI(DataServiceInterface dataService, DataTypeServiceInterface dataTypeService, ProcessingServiceInterface processingService) {
        this.dataService = dataService;
        this.dataTypeService = dataTypeService;
        this.processingService = processingService;
    }

    @PostMapping(path = "/data")
    public DataResponseDTO save(@RequestBody DataRequestDTO dataRequestDTO) { return this.dataService.save(dataRequestDTO); }
    @PostMapping(path = "/datatype")
    public DataTypeResponseDTO save(@RequestBody DataTypeRequestDTO dataTypeRequestDTO) { return this.dataTypeService.save(dataTypeRequestDTO); }

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
    public Collection<ProcessingPersonalDataDTO> getProcessingPersonalDataListPurposes(@PathVariable String idRef) {
        return processingService.getProcessingPersonalDataListPurposes(idRef);
    }

    @GetMapping(path = "/data/processedIndirectAndProducedPersonalDataList/{idRef}")
    public List<ProcessedIndirectAndProducedPersonalDataDTO> getProcessedIndirectAndProducedPersonalDataList(@PathVariable String idRef) {
        return dataService.getProcessedIndirectAndProducedPersonalDataList(idRef);
    }

    /**
     * Get the list of secondary actors for whom the processed data is transferred,
     * and the list of processed data transferred to them.
     * @param idRef the ID of the user
     * @return the list of secondary actors
     **/
    @GetMapping(path = "/data/processedPersonalDataList/transfer/{idRef}")
    public List<DataListTransferDTO> getProcessedPersonalDataListTransfer(@PathVariable String idRef) {
        return dataService.getProcessedPersonalDataListTransfer(idRef);
    }
}
