package org.aibles.interceptor.dto.request;

import javax.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class LoginRequest {
  private String username;

  private String password;
}
