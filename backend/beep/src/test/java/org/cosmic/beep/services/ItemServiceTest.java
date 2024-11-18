package org.cosmic.beep.services;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.Optional;
import org.cosmic.beep.dtos.ItemRegisterForm;
import org.cosmic.beep.entities.Category;
import org.cosmic.beep.repositories.CategoryRepository;
import org.cosmic.beep.repositories.ItemRepository;
import org.cosmic.beep.repositories.LocationRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class ItemServiceTest {

  @Mock
  private CategoryRepository categoryRepository;

  @Mock
  private LocationRepository locationRepository;

  @Mock
  private ItemRepository itemRepository;

  @InjectMocks
  private ItemService itemService;

  @Test
  @DisplayName("카테고리가 디비에 없을 때 오류 발생")
  public void noCategoryExceptionTest() {
    when(categoryRepository.findByName(anyString())).thenReturn(Optional.empty());
    ItemRegisterForm form = new ItemRegisterForm("name", "desc", "thum", "no", "lo");

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      itemService.register(form);
    });
  }

  @Test
  @DisplayName("위치가 디비에 없을 때 오류 발생")
  public void noLocationExceptionTest() {
    when(categoryRepository.findByName(anyString())).thenReturn(
        Optional.of(Category.from("name", 3L, 1L)));
    when(locationRepository.findByName(anyString())).thenReturn(Optional.empty());
    ItemRegisterForm form = new ItemRegisterForm("name", "desc", "thum", "no", "lo");

    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      itemService.register(form);
    });
  }

  @Test
  @DisplayName("request body에 문제가 있는 경우")
  public void requestBodyTest() {
    ItemRegisterForm form = new ItemRegisterForm(null, "desc", "thum", "no", "lo");
    Assertions.assertThrows(IllegalArgumentException.class, () -> itemService.register(form));
  }
}
