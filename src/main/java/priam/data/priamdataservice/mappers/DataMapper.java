package priam.data.priamdataservice.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import priam.data.priamdataservice.dto.DataRequestDTO;
import priam.data.priamdataservice.dto.DataResponseDTO;
import priam.data.priamdataservice.entities.Data;
import priam.data.priamdataservice.entities.DataType;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DataMapper {

    //@Mapping(target = "dataType"/*, ignore = true*/, source = "data.dataType")

    //@Mapping(target = "data.dataType.dataList", ignore = true)

    @Mapping(target = "data_type_name", source = "data.dataType.dataTypeName")
    @Mapping(target = "data_type_id", source = "data.dataType.dataTypeId")
    @Mapping(target = "dsCategory", source = "data.dsCategory")
    DataResponseDTO DataToDataResponseDTO(Data data);

    //@Mapping(target = "locationId", source = "dataRequestDTO.locationId")
    @Mapping(target = "dataType.dataTypeId", source = "dataRequestDTO.data_type_id")
    @Mapping(target = "dsCategory.dscId", source = "dataRequestDTO.dscId")
    Data DataRequestDTOToData(DataRequestDTO dataRequestDTO);

}
