
/**
 * Login Request Payload 
 * 
 * @author Leakhena SUON
 * @version 1.0
 * @since 2024-04-30
 */
package com.user.management.payload.request;

import jakarta.validation.constraints.NotBlank;

public class LoginRequest {
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
}
