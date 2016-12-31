package com.cakecatalog.srv.auth.client;

import com.cakecatalog.srv.auth.client.model.CreatePortalUserRequest;
import com.cakecatalog.srv.auth.client.model.LoginRequest;
import com.cakecatalog.srv.auth.client.model.PortalUserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

public class AuthClient {

  private static final Logger log = LoggerFactory.getLogger(AuthClient.class);

  public static final String LOGIN_URL = "/login";
  public static final String PORTAL_USERS_URL = "/portalUsers";

  private String url;
  private RestTemplate restTemplate;

  AuthClient() {
    this("http://localhost:8008/");
  }

  /**
   * Pass the base URL to the AuthService
   * @param url
   */
  AuthClient(String url) {
    this.url = url;
    restTemplate = new RestTemplate();
  }

  /**
   * Checks user credentials
   *
   * @param loginRequest - email and pass
   * @return logged user object or null if not succesful
   */
  public PortalUserResponse login(LoginRequest loginRequest) {
    log.info("Trying to log for user: " + loginRequest.getEmail());
    PortalUserResponse loggedUser = restTemplate.postForObject(
      url + LOGIN_URL,
      loginRequest,
      PortalUserResponse.class);
    return loggedUser;
  }

  /**
   * Create a new user
   * @param name
   * @param email
   * @param password
   * @return result object
   */
  public PortalUserResponse createPortalUser(String name, String email, String password) {
    log.info("Trying to create user: " + email);

    PortalUserResponse createdUser = restTemplate.postForObject(
      url + PORTAL_USERS_URL,
      new CreatePortalUserRequest(name, email, password),
      PortalUserResponse.class);

    createdUser.setPassword(null);//hide password

    return createdUser;
  }

  /**
   * Update an existing user
   * @param userId
   * @param name
   * @param password
   */
  public void updatePortalUser(Long userId, String name, String password) {
    log.info("Trying to updating existing user with id: " + userId);

    PortalUserResponse portalUser = getPortalUser(userId);
    portalUser.setName(name);
    portalUser.setPassword(password);

    //this updates the whole object, thus we get the whole user object first
    restTemplate.put(
      url + PORTAL_USERS_URL + "/" + userId,
      portalUser);
  }

  /**
   * Get a user object by its ID
   * @param userId
   * @return
   */
  public PortalUserResponse getPortalUser(Long userId) {
    log.info("Trying to get user with id: " + userId);

    PortalUserResponse user = restTemplate.getForObject(
      URI.create(url + PORTAL_USERS_URL + "/" + userId),
      PortalUserResponse.class);

    user.setPassword(null);//hide password

    return user;
  }

  //TODO need a client test
  public static void main(String args[]) {
    testUpdate();
    testCreate();
    testLogin();
  }

  private static void testUpdate() {
    log.info("Create + Updating + getting");
    AuthClient authClient = new AuthClient();

    PortalUserResponse createdUser = authClient.createPortalUser("name", "email", "pass");

    authClient.updatePortalUser(createdUser.getId(), "email-100", "pass-100");

    assert authClient.getPortalUser(createdUser.getId()).getEmail() == "email-100";
  }

  private static void testCreate() {
    PortalUserResponse createdPortalUser = new AuthClient().createPortalUser("name", "email", "pass");
    log.info("Creation was successful?: " + createdPortalUser);
  }

  private static void testLogin() {
    PortalUserResponse user = new AuthClient().login(new LoginRequest("sample@email.com", "samplePassword"));
    log.info("Login is successful?: " + (user != null));

    PortalUserResponse user2 = new AuthClient().login(new LoginRequest("sample@email.com", "wrong-pass"));
    log.info("Login is successful?: " + (user2 != null));
  }

}
