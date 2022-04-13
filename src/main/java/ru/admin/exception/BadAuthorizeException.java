package ru.admin.exception;

import org.springframework.http.HttpStatus;

public class BadAuthorizeException extends AppException {

    @Override
    public HttpStatus getStatus() {
        return HttpStatus.UNAUTHORIZED;
    }

}
