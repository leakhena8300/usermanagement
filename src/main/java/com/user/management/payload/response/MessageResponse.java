/**
 * Message Respon Payload 
 * 
 * @author Leakhena SUON
 * @version 1.0
 * @since 2024-04-30
 */

package com.user.management.payload.response;

public class MessageResponse {
  private String message;

  public MessageResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
