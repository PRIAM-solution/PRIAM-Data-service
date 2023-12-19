package priam.data.priamdataservice.services;

import priam.data.priamdataservice.dto.DataTypeRequestDTO;
import priam.data.priamdataservice.dto.DataTypeResponseDTO;

public interface DataTypeServiceInterface {
    DataTypeResponseDTO save(DataTypeRequestDTO dataTypeRequestDTO);
}
