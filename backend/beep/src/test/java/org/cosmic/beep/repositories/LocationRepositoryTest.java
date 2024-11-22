package org.cosmic.beep.repositories;

import java.util.Optional;
import org.cosmic.beep.entities.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class LocationRepositoryTest {

  @Autowired
  private LocationRepository locationRepository;


  @Test
  @DisplayName("위치 생성 확인")
  public void locationCreationTest() {
    locationRepository.save(Location.from("M-01", "this is good"));

    Optional<Location> location = locationRepository.findByName("M-01");

    Assertions.assertFalse(location.isEmpty());
  }
}
