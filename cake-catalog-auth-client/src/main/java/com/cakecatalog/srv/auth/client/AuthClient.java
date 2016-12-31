package com.cakecatalog.srv.auth.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class AuthClient {

  private static final Logger log = LoggerFactory.getLogger(AuthClient.class);

  private String url = "http://localhost:8008/";

  AuthClient() {}

  AuthClient(String url) {
    this.url = url;
  }

  //TODO need a client test
  public boolean login(LoginRequest loginRequest) {
    RestTemplate restTemplate = new RestTemplate();
    Boolean isLoginSuccessful = restTemplate.postForObject(
      url + "/login",
      loginRequest,
      Boolean.class);
    return isLoginSuccessful;
  }

  public static void main(String args[]) {
    boolean isSuccess = new AuthClient().login(new LoginRequest("sample@email.com", "samplePassword"));
    log.info("Login is successful?: " + isSuccess);
  }

  static public class LoginRequest {
    public String email;
    public String password;

    public LoginRequest() {
    } // JAXB needs this

    public LoginRequest(String name, String age) {
      this.email = name;
      this.password = age;
    }

    @Override
    public String toString() {
      return "Email: " + email + "; Password: " + password;
    }

  }
}
