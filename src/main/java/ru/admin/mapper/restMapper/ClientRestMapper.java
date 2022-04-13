package ru.admin.mapper.restMapper;

import org.mapstruct.Mapper;
import ru.admin.model.FintesClient;
import ru.crm.rest.admin.openapi.model.ClientInfo;

@Mapper
public interface ClientRestMapper {

    ClientInfo getClientInfoDtoFromClient(FintesClient src);
}
