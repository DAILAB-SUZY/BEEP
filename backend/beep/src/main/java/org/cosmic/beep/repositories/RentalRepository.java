package org.cosmic.beep.repositories;

import java.util.Collection;
import java.util.List;
import org.cosmic.beep.entities.Item;
import org.cosmic.beep.entities.Rental;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface RentalRepository extends JpaRepository<Rental, Long> {

  List<Rental> findByMember_Username(@NonNull String id);

  List<Rental> findByItem_IdIn(Collection<Long> ids);

  List<Rental> deleteByItemIn(Collection<Item> items);

  List<Rental> deleteByItem_IdIn(List<Long> itemsId);
}
