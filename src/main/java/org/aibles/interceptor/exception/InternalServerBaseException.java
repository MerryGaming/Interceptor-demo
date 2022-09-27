package org.aibles.interceptor.exception;

import org.springframework.http.HttpStatus;


public class InternalServerBaseException extends BaseExceptionRequest {
    public InternalServerBaseException() {
        setStatusException(HttpStatus.NOT_FOUND.value());
        setCode("org.aibles.interceptor.exception.InternalServerBaseException");
    }

}
