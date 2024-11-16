package org.cosmic.beep.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.repositories.RentalRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

  private final RentalRepository rentalRepository;

  public List<RentalDetail> getMemberRentals(@NonNull Long memberId) {
    return RentalDetail.from(rentalRepository.findByMember_Id(memberId));
  }
}
