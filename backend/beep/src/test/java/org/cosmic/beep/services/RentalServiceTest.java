package org.cosmic.beep.services;

import static org.mockito.Mockito.when;

import java.util.List;
import org.cosmic.beep.repositories.RentalRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class RentalServiceTest {

  @Mock
  private RentalRepository rentalRepository;

  @InjectMocks
  private RentalService rentalService;

  @Test
  @DisplayName("빌린 것이 없을 때")
  void getMemberRentals() {
    when(rentalRepository.findByMember_Id(1L)).thenReturn(List.of());
    Assertions.assertEquals(0, rentalService.getMemberRentals(1L).size());
  }
}