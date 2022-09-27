package org.aibles.interceptor.service;


import org.aibles.interceptor.dto.request.LoginRequest;
import org.aibles.interceptor.dto.request.Signupequest;
import org.aibles.interceptor.dto.response.LoginResponse;
import org.aibles.interceptor.dto.response.MessageResponse;
import org.aibles.interceptor.dto.response.UserResponse;

public interface LoginService {

  /**
   * register user
   * @param request - sign up from client
   * @return - sign up user
   */
  MessageResponse register(Signupequest request);

  /**
   * login a user to system
   * @param request - login request from client
   * @return - user sign in home
   */
  UserResponse login(LoginRequest request);

  /**
   * logout a user
   * @param authorizationHeader - header info from client
   * @return user sign out
   */
  MessageResponse logout(String authorizationHeader);


  /**
   * get a user by username
   * @param username - username of user
   * @return response to authenticate user
   */
  LoginResponse getByUsername(String username);

}
