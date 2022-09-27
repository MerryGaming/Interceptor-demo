package org.aibles.interceptor.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseExceptionRequest{

  public UnauthorizedException() {
    super();
    setCode("org.aibles.interceptor.exception.UnauthorizedException");
    setStatusException(HttpStatus.UNAUTHORIZED.value());
  }
}
