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
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;
  private String name;
  private String description;
  private String thumbnail;
  private Boolean isLost;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;

  @OneToOne(mappedBy = "item")
  private Rental rental;

  public Instant getReturnDate(Boolean isExtension) {
    Long days = getCategory().getExpirationTime();
    if (isExtension) {
      days *= 2;
    }
    return Instant.now().plus(days, ChronoUnit.DAYS);
  }
}
