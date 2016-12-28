package com.cakecatalog.srv.auth.service;

import com.cakecatalog.srv.auth.domain.PortalUser;
import com.cakecatalog.srv.auth.domain.PortalUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PortalUserService {

  @Autowired
  PortalUserRepository repository;

  PortalUser createUser(String name, String email, String password) {
    PortalUser portalUser = new PortalUser();
    return repository.save(portalUser);
  }

}
