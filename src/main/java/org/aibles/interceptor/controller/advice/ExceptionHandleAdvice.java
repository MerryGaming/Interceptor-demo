package org.aibles.interceptor.controller.advice;


import java.time.Instant;
import lombok.extern.slf4j.Slf4j;

import org.aibles.interceptor.exception.BaseExceptionRequest;
import org.aibles.interceptor.exception.response.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
@Slf4j
public class ExceptionHandleAdvice {

  @ExceptionHandler(value = {BaseExceptionRequest.class})
  public ResponseEntity<ExceptionResponse> exceptionHandle(
      BaseExceptionRequest error) {
    log.info("(Exception)exception: {}", error);
    ExceptionResponse response = new ExceptionResponse();
    response.setError("Exception");
    response.setMessage(error.getMessage());
    response.setTimestamp(Instant.now());
    return ResponseEntity.status(HttpStatus.valueOf(error.getStatusException())).body(response);
  }

  @ExceptionHandler(value = {MethodArgumentNotValidException.class})
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ExceptionResponse validationExceptionHandle() {
    log.info("(Validation)");
    ExceptionResponse response = new ExceptionResponse();
    response.setError("Exception");
    response.setMessage("Error input");
    response.setTimestamp(Instant.now());
    return response;
  }


}

