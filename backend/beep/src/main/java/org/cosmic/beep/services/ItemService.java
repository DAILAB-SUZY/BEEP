package org.cosmic.beep.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.repositories.ItemRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public List<RentalDetail> getItems(
      Boolean isRentable,
      @NonNull String keyword,
      @NonNull Integer pageNumber,
      @NonNull Integer pageSize) {
    if (isRentable) {
      return RentalDetail.fromItem(itemRepository.findByNameContainsIgnoreCaseAndRentalNull(keyword,
          PageRequest.of(pageNumber, pageSize)).getContent());
    }
    return RentalDetail.fromItem(itemRepository.findByNameContainsIgnoreCase(keyword,
        PageRequest.of(pageNumber, pageSize)).getContent());
  }

  public RentalDetail getItemDetail(@NonNull Long itemId) {
    return RentalDetail.from(
        itemRepository.findById(itemId).orElseThrow(IllegalArgumentException::new));
  }
}
