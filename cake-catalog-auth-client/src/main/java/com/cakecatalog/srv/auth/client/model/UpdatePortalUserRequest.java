package com.cakecatalog.srv.auth.client.model;

public class UpdatePortalUserRequest {
  private String name;
  private String password;

  public UpdatePortalUserRequest(String name, String password) {
    this.setName(name);
    this.setPassword(password);
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
