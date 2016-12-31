package com.cakecatalog.srv.auth.client.model;

public class CreatePortalUserRequest {
  private String name;
  private String email;
  private String password;

  public CreatePortalUserRequest(String name, String email, String password) {
    this.setName(name);
    this.setEmail(email);
    this.setPassword(password);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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
