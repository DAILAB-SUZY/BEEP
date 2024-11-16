package org.cosmic.beep.dtos;

import java.util.List;
import org.cosmic.beep.entities.Rental;
import org.springframework.lang.NonNull;

public record RentalDetail(
    ItemDetail item,
    RentalInfo rental
) {

  public static RentalDetail from(@NonNull Rental rental) {
    return new RentalDetail(
        ItemDetail.from(rental.getItem()),
        RentalInfo.from(rental)
    );
  }

  public static List<RentalDetail> from(List<Rental> rentals) {
    return rentals.stream().map(RentalDetail::from).toList();
  }
}
