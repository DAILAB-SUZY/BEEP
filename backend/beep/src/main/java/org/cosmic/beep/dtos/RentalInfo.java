package org.cosmic.beep.dtos;

import java.time.Instant;
import org.cosmic.beep.entities.Rental;
import org.springframework.lang.NonNull;

public record RentalInfo(
    Instant returnDate,
    Boolean isExtension
) {

  public static RentalInfo from(@NonNull Rental rental) {
    return new RentalInfo(rental.getReturnDate(), rental.getIsExtension());
  }
}
