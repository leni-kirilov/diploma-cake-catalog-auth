package com.cakecatalog.srv.auth.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class PortalUser {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  Long id;

  @Version
  Long version;

  public String name;
  public String email;
  public String password;
}
