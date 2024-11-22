package org.cosmic.beep.services;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.ItemDetail;
import org.cosmic.beep.dtos.ItemRegisterForm;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.entities.Category;
import org.cosmic.beep.entities.Item;
import org.cosmic.beep.entities.Location;
import org.cosmic.beep.repositories.CategoryRepository;
import org.cosmic.beep.repositories.ItemRepository;
import org.cosmic.beep.repositories.LocationRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  private final CategoryRepository categoryRepository;
  private final LocationRepository locationRepository;

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

  @Transactional
  public ItemDetail register(@Valid ItemRegisterForm item) {
    Category category = categoryRepository.findByName(item.category())
        .orElseThrow(IllegalArgumentException::new);
    Location location = locationRepository.findByName(item.location())
        .orElseThrow(IllegalArgumentException::new);
    return ItemDetail.from(
        itemRepository.save(Item.from(item.name(), item.description(), category, location)));
  }
}
