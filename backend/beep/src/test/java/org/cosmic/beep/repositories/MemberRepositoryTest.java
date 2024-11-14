package org.cosmic.beep.repositories;

import jakarta.transaction.Transactional;
import java.util.List;
import org.cosmic.beep.entities.Member;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class MemberRepositoryTest {

  @Autowired
  private MemberRepository memberRepository;

  @Test
  @DisplayName("유저 생성 테스트")
  @Transactional
  public void memberCreationTest() {
    memberRepository.save(Member.from("testman"));
    List<Member> member = memberRepository.findByUsername("testman");
    Assertions.assertFalse(member.isEmpty());
  }

  @Test
  @DisplayName("유저 기본 빌린물품 개수 테스트")
  @Transactional
  public void memberDefaultRentalTest() {
    memberRepository.save(Member.from("testman"));
    List<Member> member = memberRepository.findByUsername("testman");
    Assertions.assertEquals(0, member.get(0).getRentals().size());
  }
}
