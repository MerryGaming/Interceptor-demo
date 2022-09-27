package org.aibles.interceptor.dto.response;


import javax.validation.constraints.NotBlank;
import org.aibles.interceptor.entity.User;

public class UserResponse {

  private long id;
  @NotBlank
  private String username;

  @NotBlank
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public static UserResponse from(User user) {
    UserResponse response = new UserResponse();
    response.setId(user.getId());
    response.setUsername(user.getUsername());
    response.setPassword(user.getPassword());
    return response;

  }

}
