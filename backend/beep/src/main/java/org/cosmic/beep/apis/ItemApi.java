package org.cosmic.beep.apis;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.ItemDetail;
import org.cosmic.beep.dtos.ItemRegisterForm;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.services.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class ItemApi {

  private final ItemService itemService;

  @PostMapping("item")
  public ItemDetail registerItem(@Valid @RequestBody ItemRegisterForm item) {
    return itemService.register(item);
  }

  @GetMapping("item/{itemId}")
  public RentalDetail getItemInfo(@PathVariable Long itemId) {
    return itemService.getItemDetail(itemId);
  }

  @GetMapping("item")
  public List<RentalDetail> getItems(
      @RequestParam(defaultValue = "false") Boolean isRentable,
      @RequestParam(defaultValue = "0") Integer page,
      @RequestParam(defaultValue = "10") Integer size,
      @RequestParam(defaultValue = "") String search) {
    return itemService.getItems(isRentable, search, page, size);
  }
}