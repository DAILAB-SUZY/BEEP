package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.lang.NonNull;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;
  private String description;
  private String thumbnail;
  private Boolean isLost;

  @ManyToOne
  @JoinColumn(name = "category_id", nullable = false)
  private Category category;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  @OneToOne(mappedBy = "item")
  private Rental rental;

  public static Item from(
      @NonNull String name,
      @NonNull String description,
      @NonNull Category category,
      @NonNull Location location
  ) {
    return Item.builder()
        .name(name)
        .description(description)
        .category(category)
        .location(location)
        .build();
  }

  public Instant getReturnDate(@NonNull Boolean isExtension) {
    Long days = getCategory().getExpirationTime();
    if (isExtension) {
      days *= 2;
    }
    return Instant.now().plus(days, ChronoUnit.DAYS);
  }

  public String getCategoryName() {
    return getCategory().getName();
  }

  public String getLocationName() {
    return getLocation().getName();
  }

  public Long getRentPeriod() {
    return getCategory().getExpirationTime();
  }

  public Boolean isRentalAvailable() {
    return getRental() == null;
  }
}
