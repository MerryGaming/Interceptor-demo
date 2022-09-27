package org.aibles.interceptor.exception;

import org.springframework.http.HttpStatus;


public class NotFoundBaseException extends BaseExceptionRequest {
    public NotFoundBaseException(Object field) {
        setStatusException(HttpStatus.NOT_FOUND.value());
        setCode("org.aibles.interceptor.exception.NotFoundBaseException");
        addParams("field", field);

    }
}
