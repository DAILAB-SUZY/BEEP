package org.cosmic.beep.services;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.dtos.RentalInfo;
import org.cosmic.beep.entities.Member;
import org.cosmic.beep.entities.Rental;
import org.cosmic.beep.entities.RentalLog;
import org.cosmic.beep.repositories.ItemRepository;
import org.cosmic.beep.repositories.MemberRepository;
import org.cosmic.beep.repositories.RentalLogRepository;
import org.cosmic.beep.repositories.RentalRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

  private final RentalRepository rentalRepository;
  private final MemberRepository memberRepository;
  private final ItemRepository itemRepository;
  private final RentalLogRepository rentalLogRepository;

  public List<RentalDetail> getMemberRentals(@NonNull String memberName) {
    return RentalDetail.from(rentalRepository.findByMember_Username(memberName));
  }

  @Transactional
  public void rentalItem(List<Long> itemsId, @NonNull String memberName) {
    Member member = memberRepository.findByUsername(memberName)
        .orElseThrow(EntityNotFoundException::new);
    List<Rental> rentals = itemRepository.findByIdIn(itemsId).stream()
        .map(item -> Rental.from(member, item))
        .toList();
    rentalLogRepository.saveAll(rentals.stream().map(RentalLog::from).toList());
    rentalRepository.saveAll(rentals);
  }

  @Transactional
  public void returnItem(List<Long> itemsId) {
    List<Rental> deleted = rentalRepository.deleteByItem_IdIn(itemsId);
    rentalLogRepository.findByRentalIn(deleted).forEach(RentalLog::returnItem);
    rentalRepository.deleteAll();
  }

  @Transactional
  public RentalInfo expendDuration(Long itemId, String username) {
    Rental rental = rentalRepository.findByMember_UsernameAndItem_Id(username, itemId)
        .orElseThrow(IllegalArgumentException::new);
    rental.extend();
    return RentalInfo.from(rental);
  }
}
