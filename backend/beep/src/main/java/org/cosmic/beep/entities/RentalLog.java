package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RentalLog {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  private Instant rentDate;

  private Instant returnDate;

  @OneToOne(mappedBy = "rentalLog")
  private Rental rental;

  @PrePersist
  public void perPersist() {
    setRentDate(Instant.now());
  }

  public static RentalLog from(Rental rental) {
    return RentalLog.builder()
        .member(rental.getMember())
        .item(rental.getItem())
        .build();
  }

  public void returnItem() {
    setReturnDate(Instant.now());
  }
}
