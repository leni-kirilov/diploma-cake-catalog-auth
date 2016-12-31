package com.cakecatalog.srv.auth.web;

import com.cakecatalog.srv.auth.domain.LoginRequest;
import com.cakecatalog.srv.auth.domain.PortalUser;
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

@Controller
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PortalUserController {

  @Autowired
  AuthService authService;

  @POST
  @RequestMapping("/login")
  @ResponseBody
  PortalUser login(@RequestBody LoginRequest loginRequest) {
    return authService.login(loginRequest.email, loginRequest.password);
  }

  //TODO move liquibase changelog to this app
}
