package org.cosmic.beep.apis;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.ItemDetail;
import org.cosmic.beep.dtos.ItemRegisterForm;
import org.cosmic.beep.services.ItemService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
}