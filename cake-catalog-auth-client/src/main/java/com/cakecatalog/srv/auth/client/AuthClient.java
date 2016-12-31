package com.cakecatalog.srv.auth.client;

import com.cakecatalog.srv.auth.client.model.CreatePortalUserRequest;
import com.cakecatalog.srv.auth.client.model.CreatePortalUserResponse;
import com.cakecatalog.srv.auth.client.model.LoginRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

public class AuthClient {

  private static final Logger log = LoggerFactory.getLogger(AuthClient.class);

  public static final String LOGIN_URL = "/login";
  public static final String PORTAL_USERS_URL = "/portalUsers";

  private String url;
  private RestTemplate restTemplate;

  AuthClient() {
    this("http://localhost:8008/");
  }

  AuthClient(String url) {
    this.url = url;
    restTemplate = new RestTemplate();
  }

  public boolean login(LoginRequest loginRequest) {
    log.info("Trying to log for user: " + loginRequest.getEmail());
    Boolean isLoginSuccessful = restTemplate.postForObject(
      url + LOGIN_URL,
      loginRequest,
      Boolean.class);
    return isLoginSuccessful;
  }

  public CreatePortalUserResponse createPortalUser(String name, String email, String password) {
    log.info("Trying to create user: " + email);

    CreatePortalUserResponse createdUser = restTemplate.postForObject(
      url + PORTAL_USERS_URL,
      new CreatePortalUserRequest(name, email, password),
      CreatePortalUserResponse.class);

    return createdUser;
  }






  //TODO need a client test
  public static void main(String args[]) {
    testCreate();
//    testLogin();
  }

  private static void testCreate() {
    CreatePortalUserResponse createdPortalUser = new AuthClient().createPortalUser("name", "email", "pass");
    log.info("Creation was successful?: " + createdPortalUser);
  }

  private static void testLogin() {
    boolean isSuccess = new AuthClient().login(new LoginRequest("sample@email.com", "samplePassword"));
    log.info("Login is successful?: " + isSuccess);
  }

}
