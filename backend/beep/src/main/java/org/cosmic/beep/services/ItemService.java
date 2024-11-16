package org.cosmic.beep.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.ItemDetail;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.repositories.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public List<ItemDetail> getRentAvailableItems() {
    return ItemDetail.from(itemRepository.findByRentalNull());
  }

  public List<RentalDetail> getItems(@NonNull String keyword,
      @NonNull Integer pageNumber,
      @NonNull Integer pageSize) {
    return RentalDetail.fromItem(itemRepository.findByNameContainsIgnoreCase(keyword,
        PageRequest.of(pageNumber, pageSize)));
  }

  public RentalDetail getItemDetail(@NonNull Long itemId) {
    return RentalDetail.from(
        itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new));
  }
}
