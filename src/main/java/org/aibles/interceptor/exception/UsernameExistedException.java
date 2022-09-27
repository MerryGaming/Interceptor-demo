package org.aibles.interceptor.exception;

import org.springframework.http.HttpStatus;

public class UsernameExistedException extends BaseExceptionRequest{

  public UsernameExistedException(String username) {
    setStatusException(HttpStatus.NOT_FOUND.value());
    setCode("org.aibles.interceptor.exception.BadRequestBaseException");
    addParams("username", username);
  }
}
