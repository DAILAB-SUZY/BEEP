package org.cosmic.beep.dtos;

import java.util.List;
import org.cosmic.beep.entities.Item;
import org.springframework.lang.NonNull;

public record ItemDetail(
    Long id,
    String name,
    String description,
    String thumbnail,
    String category,
    String location,
    Boolean lost,
    Long rentPeriod,
    Boolean isRentable
) {

  public static ItemDetail from(@NonNull Item item) {
    return new ItemDetail(
        item.getId(),
        item.getName(),
        item.getDescription(),
        item.getThumbnail(),
        item.getCategoryName(),
        item.getLocationName(),
        item.getIsLost(),
        item.getRentPeriod(),
        item.isRentalAvailable()
    );
  }

  public static List<ItemDetail> from(List<Item> items) {
    return items.stream().map(ItemDetail::from).toList();
  }
}
