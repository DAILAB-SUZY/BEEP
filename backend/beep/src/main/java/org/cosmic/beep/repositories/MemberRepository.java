package org.cosmic.beep.repositories;

import java.util.List;
import org.cosmic.beep.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  List<Member> findByUsername(String username);
}
