package org.cosmic.beep.repositories;

import java.util.Collection;
import java.util.List;
import org.cosmic.beep.entities.Item;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findByRentalNull();

  Page<Item> findByNameContainsIgnoreCase(@NonNull String name, Pageable pageable);

  List<Item> findByIdIn(Collection<Long> ids);

  List<Item> findByNameContainsAndRentalNotNull(String name, Pageable pageable);

  Page<Item> findByNameContainsIgnoreCaseAndRentalNull(String name, Pageable pageable);
}
