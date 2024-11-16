package org.cosmic.beep.repositories;

import java.util.List;
import org.cosmic.beep.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findByRentalNull();
}
