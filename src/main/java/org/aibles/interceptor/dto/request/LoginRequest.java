package org.aibles.interceptor.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
  @NotBlank(message = "username must not blank")
  private String username;

  @NotBlank(message = "password must not blank")
  private String password;
}
