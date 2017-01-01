package com.cakecatalog.srv.auth.config;

import com.cakecatalog.srv.auth.domain.PortalUser;
import org.slf4j.LoggerFactory;
import org.springframework.data.rest.core.annotation.HandleBeforeCreate;
import org.springframework.data.rest.core.annotation.HandleBeforeSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@RepositoryEventHandler
public class PortalUserEventHandler {

  private static final org.slf4j.Logger log = LoggerFactory.getLogger(PortalUserEventHandler.class);

  @HandleBeforeSave
  public void handlePortalUserSave(PortalUser p) {
    log.info("Saving portal user: " + p.getId());
  }

  @HandleBeforeCreate
  public void handlePortalUserCreate(PortalUser p) {
    log.info("Creating portal user: " + p.getEmail());

  }
}
