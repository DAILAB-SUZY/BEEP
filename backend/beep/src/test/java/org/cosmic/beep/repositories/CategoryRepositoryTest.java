package org.cosmic.beep.repositories;

import java.util.Optional;
import org.cosmic.beep.entities.Category;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class CategoryRepositoryTest {

  @Autowired
  private CategoryRepository categoryRepository;


  @Test
  @DisplayName("카테고리 생성 확인")
  public void categoryCreationTest() {
    categoryRepository.save(Category.from("A-01", 3L, 7L));

    Optional<Category> category = categoryRepository.findByName("A-01");

    Assertions.assertFalse(category.isEmpty());
  }
}
