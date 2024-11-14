package org.cosmic.beep.repositories;

import java.util.Optional;
import org.cosmic.beep.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  Optional<Category> findByName(String name);
}
