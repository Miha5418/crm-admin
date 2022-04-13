package ru.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import ru.admin.mapper.ClientMapper;
import ru.admin.model.FintesClient;
import ru.admin.repository.ClientRepository;
import ru.crm.rest.admin.openapi.model.ClientInfo;
import ru.crm.rest.admin.openapi.model.ClientInfoPageable;
import ru.crm.rest.admin.openapi.model.PageParams;

@Slf4j
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepo;
    private final ClientMapper clientMapper;

    /**
     * Добавление нового клиента
     *
     * @param clientInfo ДТО клиента
     * @return Сохранённый клиент
     */
    public FintesClient createClient(ClientInfo clientInfo) {
        log.info("Создание клиента с абонементом {}", clientInfo.getMemberShipNumber());

        FintesClient save = null;
        val client = clientMapper.getClientDocumentFromClientInfoDto(clientInfo);

        try {
            save = clientRepo.save(client);
            log.info("Клиент сохранён в базу");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return save;
    }

    /**
     * Получение клиента по идентификатору
     *
     * @param id Идентификатор клиента
     * @return Клиент
     * @throws ChangeSetPersister.NotFoundException Клиента не найден
     */
    public FintesClient getClientById(Integer id) throws ChangeSetPersister.NotFoundException {

        return clientRepo.findById(id)
                .orElseThrow(ChangeSetPersister.NotFoundException::new);

    }


    public ClientInfoPageable getClientList(PageParams pageParams) {

        val result = clientRepo.findAll();

        return clientMapper.getClientInfoPageableFromFitnesClienList(result);


    }


}
