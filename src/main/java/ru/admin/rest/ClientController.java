package ru.admin.rest;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.admin.mapper.restMapper.ClientRestMapper;
import ru.admin.service.ClientService;
import ru.crm.rest.admin.openapi.model.ClientInfo;
import ru.crm.rest.admin.openapi.model.ClientInfoPageable;
import ru.crm.rest.admin.openapi.model.PageParams;
import ru.crm.rest.admin.openapi.support.ClientApi;
import ru.crm.rest.admin.openapi.support.ClientApiDelegate;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ClientController implements ClientApi {

    private final ClientService clientService;
    private final ClientRestMapper clientRestMapper;

    @Override
    public ClientApiDelegate getDelegate() {
        return ClientApi.super.getDelegate();
    }

    @Override
    @PreAuthorize("permitAll()")
    public ResponseEntity<ClientInfo> addNewClient(@Valid ClientInfo clientInfo) {
        log.info("Call method addNewClient");
        val result = clientService.createClient(clientInfo);
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientRestMapper.getClientInfoDtoFromClient(result));
    }

    @SneakyThrows
    @Override
    @PreAuthorize("permitAll()")
    public ResponseEntity<ClientInfo> getClientInfoById(Integer clientId) {
        val result = clientService.getClientById(clientId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(clientRestMapper.getClientInfoDtoFromClient(result));
    }

    @Override
    public ResponseEntity<ClientInfoPageable> getListClientInfo(@Valid PageParams pageParams) {
        val result = clientService.getClientList(pageParams);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }
}