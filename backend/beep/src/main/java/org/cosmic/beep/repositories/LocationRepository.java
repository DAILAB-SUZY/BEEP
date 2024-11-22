package org.cosmic.beep.repositories;

import java.util.Optional;
import org.cosmic.beep.entities.Location;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocationRepository extends JpaRepository<Location, Long> {

  Optional<Location> findByName(String name);
}
