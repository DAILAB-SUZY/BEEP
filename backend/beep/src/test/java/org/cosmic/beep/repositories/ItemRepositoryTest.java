package org.cosmic.beep.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cosmic.beep.entities.Category;
import org.cosmic.beep.entities.Item;
import org.cosmic.beep.entities.Location;
import org.cosmic.beep.entities.Member;
import org.cosmic.beep.entities.Rental;
import org.cosmic.beep.entities.RentalLog;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class ItemRepositoryTest {

  @Autowired
  private ItemRepository itemRepository;
  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  @DisplayName("대여 가능 아이템 조회")
  void findByRentalNull() {
    Member member = testEntityManager.persist(Member.from("testman"));
    Category category = testEntityManager.persist(Category.from("카테고리", 3L, 1L));
    Location location = testEntityManager.persist(Location.from("위치", "주소"));
    Item item1 = testEntityManager.persist(Item.from("아이템", "설명", category, location));
    testEntityManager.persist(Item.from("아이템2", "설명", category, location));
    testEntityManager.persist(Item.from("아이템3", "설명", category, location));
    testEntityManager.persist(Item.from("아이템4", "설명", category, location));
    Rental rental = Rental.from(member, item1);
    RentalLog log = testEntityManager.persist(RentalLog.from(rental));
    rental.setRentalLog(log);
    testEntityManager.persist(rental);

    assertEquals(3, itemRepository.findByRentalNull().size());
  }
}