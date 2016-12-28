package com.cakecatalog.srv.auth.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class Client {

  private static final Logger log = LoggerFactory.getLogger(Client.class);

  public static boolean login(LoginRequest loginRequest) {
    RestTemplate restTemplate = new RestTemplate();
    Boolean isLoginSuccessful = restTemplate.postForObject(
      "http://localhost:8080/login",
      loginRequest,
      Boolean.class);
    return isLoginSuccessful;
  }

  public static void main(String args[]) {
    boolean isSuccess = Client.login(new LoginRequest("sample@email.com", "samplePassword"));
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
