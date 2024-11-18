package org.cosmic.beep.repositories;

import java.util.Optional;
import org.cosmic.beep.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {

  Optional<Member> findByUsername(String username);
}
