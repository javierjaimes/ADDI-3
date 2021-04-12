package co.com.addi.usecase.mapper;

import co.com.addi.domain.dto.ClientDTO;
import co.com.addi.domain.entities.Client;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface MapperSalesPipeline {

    Client clientDTOtoEntityClient(ClientDTO clientDTO);

}
