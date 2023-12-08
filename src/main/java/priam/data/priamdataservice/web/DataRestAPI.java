package priam.data.priamdataservice.web;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import priam.data.priamdataservice.dto.DataRequestDTO;
import priam.data.priamdataservice.dto.DataResponseDTO;
import priam.data.priamdataservice.dto.DataTypeResponseDTO;
import priam.data.priamdataservice.entities.DataType;
import priam.data.priamdataservice.services.DataService;

import java.util.List;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping(path = "/api")
public class DataRestAPI {

    private DataService dataService;

    public DataRestAPI(DataService dataService) {
        this.dataService = dataService;
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

   /*
   @GetMapping(path = "/dataType/{id}")
    public DataTypeResponseDTO getDataType(@PathVariable int id) {
        return dataService.getDataTypeByDataId(id);
    }*/



}
