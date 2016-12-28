package com.cakecatalog.srv.auth.web;

import com.cakecatalog.srv.auth.domain.LoginRequest;
import com.cakecatalog.srv.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.annotation.XmlRootElement;

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortalUserController {

//  //TODO remove this
//  @Autowired
//  PortalUserRepository repository;

  @Autowired
  AuthService authService;


//  @GET
//  @RequestMapping("/test")
//  @ResponseBody
//  MyJaxbBean test() {
//    List<PortalUser> allUsers = repository.findAll();
//    return new MyJaxbBean("hello", allUsers.size());
//  }

  @POST
  @RequestMapping("/login")
  @ResponseBody
  Boolean login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest.email, loginRequest.password);
  }


  //TODO define REST interface - login, logout, createUser, editUser, getUser

  //TODO connect to PostgreSql DB
  //TODO create services that uses repositories

  //TODO move liquibase changelog to this app

  @XmlRootElement
  static public class MyJaxbBean {
    public String name;
    public int age;

    public MyJaxbBean() {
    } // JAXB needs this

    public MyJaxbBean(String name, int age) {
      this.name = name;
      this.age = age;
    }
  }
}
