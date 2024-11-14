package org.cosmic.beep.repositories;

import java.util.List;
import org.cosmic.beep.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

  List<Category> findByName(String name);
}
