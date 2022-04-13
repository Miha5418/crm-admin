package ru.admin.exception;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException {

    public HttpStatus getStatus() {
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }

}

