package org.cosmic.beep.services;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.ItemDetail;
import org.cosmic.beep.repositories.ItemRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;

  public List<ItemDetail> getRentAvailableItems() {
    return ItemDetail.from(itemRepository.findByRentalNull());
  }
}
