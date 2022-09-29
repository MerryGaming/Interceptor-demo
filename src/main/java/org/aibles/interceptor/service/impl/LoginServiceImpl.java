package org.aibles.interceptor.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.aibles.interceptor.dto.request.LoginRequest;
import org.aibles.interceptor.dto.request.Signupequest;
import org.aibles.interceptor.dto.response.LoginResponse;
import org.aibles.interceptor.dto.response.MessageResponse;
import org.aibles.interceptor.dto.response.UserResponse;
import org.aibles.interceptor.entity.User;
import org.aibles.interceptor.exception.BadRequestBaseException;
import org.aibles.interceptor.exception.NotFoundBaseException;
import org.aibles.interceptor.repository.LoginRepository;
import org.aibles.interceptor.service.LoginService;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
public class LoginServiceImpl implements LoginService {

  private final LoginRepository repository;

  public LoginServiceImpl(LoginRepository repository) {
    this.repository = repository;
  }

  @Override
  @Transactional
  public UserResponse login(LoginRequest request) {
    log.info("(login)username : {}", request.getUsername());
    if (!repository.existsByUsernameAndPassword(request.getUsername(), request.getPassword())) {
      throw new BadRequestBaseException(request.getUsername());
    }
    User user = repository.findByUsername(request.getUsername())
        .orElseThrow(() ->
            new NotFoundBaseException(request.getUsername()));
    user.setEnabled(true);
    return UserResponse.from(repository.save(user));
  }

  @Override
  @Transactional
  public MessageResponse register(Signupequest request) {
    log.info("(register)username : {}", request.getUsername());
    if (repository.existsByUsername(request.getUsername())) {
      return new MessageResponse("Error: Email is already taken!");
    }
    User user = request.toLogger();
    user.setEnabled(false);
    repository.save(user);
    return new MessageResponse("User registered successfully!");
  }

  @Override
  @Transactional(readOnly = true)
  public LoginResponse getByUsername(String username) {
    log.info("(getByUsername)username : {}", username);
    User user = repository
        .findByUsername(username)
        .orElseThrow(() ->
            new NotFoundBaseException(username));
    return LoginResponse.from(user);
  }

}
