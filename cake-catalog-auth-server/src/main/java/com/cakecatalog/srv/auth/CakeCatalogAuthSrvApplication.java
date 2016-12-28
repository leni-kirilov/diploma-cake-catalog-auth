package com.cakecatalog.srv.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
//@EnableAutoConfiguration
//@ComponentScan
//@EnableJpaRepositories("com.cakecatalog.srv.auth.domain")
//@EntityScan("com.cakecatalog.srv.auth.domain")
@SpringBootApplication
public class CakeCatalogAuthSrvApplication {

  //TODO define REST interface - login, logout, createUser, editUser, getUser

  //TODO connect to PostgreSql DB
  //TODO create services that uses repositories


  //TODO move liquibase changelog to this app

  public static void main(String[] args) {
    SpringApplication.run(CakeCatalogAuthSrvApplication.class, args);
  }

}
