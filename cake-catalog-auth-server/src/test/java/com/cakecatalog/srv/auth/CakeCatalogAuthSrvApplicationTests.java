package com.cakecatalog.srv.auth;

import com.cakecatalog.srv.auth.domain.PortalUser;
import com.cakecatalog.srv.auth.domain.PortalUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class CakeCatalogAuthSrvApplicationTests {

  @Autowired
  PortalUserRepository repository;

  @Test
  public void saveAndDelete() {
    assert repository.save(new PortalUser()) != null;
    assert repository.findAll().size() > 0 ;
  }

  @Test
  public void emptyDatabase() {
    assert repository.findAll().isEmpty();
  }


  //TODO make 1 REST test
}
