package com.cakecatalog.srv.auth.client.model;

public class LoginRequest {
  private String email;
  private String password;

  public LoginRequest() {
  } // JAXB needs this

  public LoginRequest(String email, String age) {
    this.setEmail(email);
    this.setPassword(age);
  }

  @Override
  public String toString() {
    return "Email: " + getEmail() + "; Password: " + getPassword();
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
