package ru.admin.rest;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import ru.admin.service.AuthService;
import ru.crm.rest.admin.openapi.model.LoginRequestMessage;
import ru.crm.rest.admin.openapi.model.LoginResponseMessage;
import ru.crm.rest.admin.openapi.support.AuthApi;

import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AuthController implements AuthApi {

    private final AuthService authService;

    @Override
    @PreAuthorize("permitAll()")
    public ResponseEntity<LoginResponseMessage> login(@Valid LoginRequestMessage loginRequestMessage) {
        log.info("RUN authLoginPost");
        val responseMessage = authService.login(loginRequestMessage);
        return ResponseEntity.status(HttpStatus.OK)
                .body(responseMessage);    }
}
