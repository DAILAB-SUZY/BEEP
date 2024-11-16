package org.cosmic.beep.repositories;

import java.util.List;
import org.cosmic.beep.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface RentalRepository extends JpaRepository<Rental, Long> {

  List<Rental> findByMember_Id(@NonNull Long id);
}
