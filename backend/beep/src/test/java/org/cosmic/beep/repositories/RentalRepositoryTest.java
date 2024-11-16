package org.cosmic.beep.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.cosmic.beep.entities.Category;
import org.cosmic.beep.entities.Item;
import org.cosmic.beep.entities.Member;
import org.cosmic.beep.entities.Rental;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class RentalRepositoryTest {

  @Autowired
  private RentalRepository rentalRepository;
  @Autowired
  private TestEntityManager testEntityManager;

  @Test
  @DisplayName("아무것도 빌린 것이 없을 때")
  void findByMemberId() {
    Member member = testEntityManager.persist(Member.from("testman"));
    assertEquals(0, rentalRepository.findByMember_Id(member.getId()).size());
  }

  @Test
  @DisplayName("하나 빌렸을 때")
  void findByMemberId2() {
    Member member = testEntityManager.persist(Member.from("testman"));
    Category category = testEntityManager.persist(Category.from("test", 3L, 14L));
    Item item = testEntityManager.persist(Item.from("M-01", "this is good", category));
    testEntityManager.persist(Rental.from(member, item));
    assertEquals(1, rentalRepository.findByMember_Id(member.getId()).size());
  }
}