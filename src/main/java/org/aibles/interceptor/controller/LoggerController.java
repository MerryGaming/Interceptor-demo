package org.aibles.interceptor.controller;

import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.aibles.interceptor.dto.request.LoginRequest;
import org.aibles.interceptor.dto.request.Signupequest;
import org.aibles.interceptor.dto.response.MessageResponse;
import org.aibles.interceptor.dto.response.UserResponse;
import org.aibles.interceptor.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
public class LoggerController {

  public final LoginService service;

  public LoggerController(LoginService service) {
    this.service = service;
  }

  @PostMapping("/login")
  @ResponseStatus(HttpStatus.OK)
  public UserResponse login(@RequestBody @Valid LoginRequest request) {
    log.info("(login)username : {}", request.getUsername());
    return service.login(request);
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public MessageResponse register(@RequestBody @Valid Signupequest request) {
    log.info("(register)username : {}", request.getUsername());
    return service.register(request);
  }



}
