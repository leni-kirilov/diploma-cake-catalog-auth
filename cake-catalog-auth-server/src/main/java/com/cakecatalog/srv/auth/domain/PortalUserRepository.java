package com.cakecatalog.srv.auth.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PortalUserRepository extends JpaRepository<PortalUser, Long> {

  PortalUser findOneByEmail(String email);
}
