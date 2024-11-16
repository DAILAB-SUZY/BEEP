package org.cosmic.beep.repositories;

import java.util.Collection;
import java.util.List;
import org.cosmic.beep.entities.Rental;
import org.cosmic.beep.entities.RentalLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalLogRepository extends JpaRepository<RentalLog, Long> {

  List<RentalLog> findByItem_IdInAndReturnDateNull(Collection<Long> ids);

  List<RentalLog> findByRentalIn(List<Rental> deleted);
}