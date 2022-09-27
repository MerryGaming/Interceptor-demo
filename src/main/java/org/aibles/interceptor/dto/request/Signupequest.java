package org.aibles.interceptor.dto.request;


import org.aibles.interceptor.entity.User;

public class Signupequest {

  private String username;
  private String password;


  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public User toLogger() {
    User user = new User();
    user.setUsername(this.getUsername());
    user.setPassword(this.getPassword());
    return user;
  }

}
