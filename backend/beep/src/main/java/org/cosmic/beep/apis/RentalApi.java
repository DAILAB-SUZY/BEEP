package org.cosmic.beep.apis;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.cosmic.beep.dtos.RentalDetail;
import org.cosmic.beep.dtos.RentalForm;
import org.cosmic.beep.dtos.RentalInfo;
import org.cosmic.beep.services.RentalService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@RequiredArgsConstructor
public class RentalApi {

  private final RentalService rentalService;

  @PostMapping("rental/{itemId}")
  public RentalInfo rentalExpansion(@PathVariable Long itemId, UserDetails userDetails) {
    return rentalService.expendDuration(itemId, userDetails.getUsername());
  }

  @GetMapping("rental")
  public List<RentalDetail> getUserRentalItems(UserDetails userDetails) {
    return rentalService.getMemberRentals(userDetails.getUsername());
  }

  @PostMapping("rental")
  public void rentalItems(@RequestBody RentalForm form, UserDetails userDetails) {
    rentalService.rentalItem(form.items(), userDetails.getUsername());
  }

  @DeleteMapping("rental")
  public void returnItems(@RequestBody RentalForm form) {
    rentalService.returnItem(form.items());
  }
}
