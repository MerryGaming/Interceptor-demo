package org.aibles.interceptor.dto.response;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import org.aibles.interceptor.entity.User;


public class LoginResponse {

  @NotBlank
  private String username;

  @NotBlank
  private String password;

  private boolean enabled;

  public boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }

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



  public static LoginResponse from(User user) {
    LoginResponse response = new LoginResponse();
    response.setUsername(user.getUsername());
    response.setPassword(user.getPassword());
    response.setEnabled(user.getEnabled());
    return response;

  }

}
