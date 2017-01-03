package com.cakecatalog.srv.auth.client;

import com.cakecatalog.srv.auth.client.model.CreatePortalUserRequest;
import com.cakecatalog.srv.auth.client.model.LoginRequest;
import com.cakecatalog.srv.auth.client.model.PortalUserResponse;
import com.cakecatalog.srv.auth.client.model.UpdatePortalUserRequest;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class AuthClient {

  private static final Logger log = LoggerFactory.getLogger(AuthClient.class);

  public static final String LOGIN_URL = "/login";
  public static final String PORTAL_USERS_URL = "/portalUsers";

  private String url;
  private String sourceId;
  private RestTemplate restTemplate;

  AuthClient(String url) {
    this(url, "AUTH_CLIENT");
  }

  /**
   * Pass the base URL to the AuthService
   *
   * @param url
   * @param sourceId - who is the caller to this service
   */
  AuthClient(String url, String sourceId) {
    log.info("Starting with url " + url);
    this.url = url;
    this.restTemplate = new RestTemplate();
    this.sourceId = sourceId;

    HttpComponentsClientHttpRequestFactory requestFactory = new HttpComponentsClientHttpRequestFactory();
    requestFactory.setConnectTimeout(10000);
    requestFactory.setReadTimeout(10000);
    CloseableHttpClient httpClient = HttpClientBuilder.create().setSSLHostnameVerifier(SSLConnectionSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER).build();
    requestFactory.setHttpClient(httpClient);

    this.restTemplate.setRequestFactory(requestFactory);
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
      new HttpEntity<>(loginRequest, getHeaders()),
      PortalUserResponse.class);
    return loggedUser;
  }

  /**
   * Create a new user
   *
   * @param name
   * @param email
   * @param password
   * @return result object
   */
  public PortalUserResponse createPortalUser(String name, String email, String password) {
    log.info("Trying to create user: " + email);

    PortalUserResponse createdUser = restTemplate.postForObject(
      url + PORTAL_USERS_URL,
      new HttpEntity<>(new CreatePortalUserRequest(name, email, password), getHeaders()),
      PortalUserResponse.class);

    return createdUser;
  }

  /**
   * Update an existing user
   *
   * @param userId
   * @param name
   * @param password
   */
  public PortalUserResponse updatePortalUser(Long userId, String name, String password) {
    log.info("Trying to updating existing user with id: " + userId);

    return restTemplate.patchForObject(
      url + PORTAL_USERS_URL + "/" + userId,
      new HttpEntity<>(new UpdatePortalUserRequest(name, password), getHeaders()),
      PortalUserResponse.class);
  }

  /**
   * Get a user object by its ID
   *
   * @param userId
   * @return
   */
  public PortalUserResponse getPortalUser(Long userId) {
    log.info("Trying to get user with id: " + userId);

    PortalUserResponse user = restTemplate.getForObject(
      URI.create(url + PORTAL_USERS_URL + "/" + userId),
      PortalUserResponse.class);

    return user;
  }

  private MultiValueMap<String, String> getHeaders() {
    MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
    List<String> values = new ArrayList<>();
    values.add(sourceId);
    headers.put("x-cake-catalog-source-id", values);
    //TODO think about request-id
    return headers;
  }

  //TODO need a client test
  public static void main(String args[]) {
    AuthClient local = new AuthClient("http://localhost:8008/");
//    AuthClient remote = new AuthClient("https://cake-catalog-auth.herokuapp.com/");

    AuthClient testClient = local;
    testUpdate(testClient);
    testCreate(testClient);
    testLogin(testClient);
  }

  private static void testUpdate(AuthClient authClient) {
    log.info("Create + Updating + getting");

    PortalUserResponse createdUser = authClient.createPortalUser("name", "email", "pass");

    PortalUserResponse updated = authClient.updatePortalUser(createdUser.getId(), "name-100", "pass-100");

    log.info("Asserting the returned object: " + updated);
    if (updated.getEmail() == null) {
      throw new RuntimeException("Email is null");
    }
    if (updated.getEmail().equals("em1ail")) {
      throw new RuntimeException("Wrong email value");
    }
    log.info("Asserts success");
  }

  private static void testCreate(AuthClient authClient) {
    PortalUserResponse createdPortalUser = authClient.createPortalUser("name", "email", "pass");
    log.info("Creation was successful?: " + createdPortalUser);
  }

  private static void testLogin(AuthClient authClient) {
    PortalUserResponse user = authClient.login(new LoginRequest("sample@email.com", "samplePassword"));
    log.info("Login is successful?: " + (user != null));

    PortalUserResponse user2 = authClient.login(new LoginRequest("sample@email.com", "wrong-pass"));
    log.info("Login is successful?: " + (user2 != null));
  }

}
