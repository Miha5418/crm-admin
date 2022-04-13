package ru.admin.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.keycloak.authorization.client.AuthorizationDeniedException;
import org.keycloak.authorization.client.AuthzClient;
import org.keycloak.authorization.client.util.HttpResponseException;
import org.springframework.stereotype.Service;
import ru.admin.exception.BadAuthorizeException;
import ru.crm.rest.admin.openapi.model.LoginRequestMessage;
import ru.crm.rest.admin.openapi.model.LoginResponseMessage;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthzClient authzClient;

    public LoginResponseMessage login(LoginRequestMessage login) {
        log.info("START login for user {}", login.getMail());
        try {
            val response = authzClient.authorization(login.getMail(), login.getPassword())
                    .authorize();
            val result = new LoginResponseMessage()
                    .tokenType(response.getTokenType())
                    .token(response.getToken());
            log.info("FINISH login for user {} successfully", login.getMail());
            return result;
        } catch (AuthorizationDeniedException | HttpResponseException ex) {
            log.debug("Exception when login {}", login.getMail(), ex);
            log.info("FINISH login for user {} is bad", login.getMail());
            throw new BadAuthorizeException();
        } catch (Exception ex) {
            log.error("Some error occurred during login");
            throw new BadAuthorizeException();
        }
    }
}
