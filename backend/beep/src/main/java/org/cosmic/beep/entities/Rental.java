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
import org.springframework.lang.NonNull;

@Entity
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Rental {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @OneToOne
  @JoinColumn(name = "item_id", nullable = false)
  private Item item;

  private Instant returnDate;

  private Boolean isExtension;

  @OneToOne
  @JoinColumn(name = "rental_log_id", nullable = false)
  private RentalLog rentalLog;

  public static Rental from(@NonNull Member member, @NonNull Item item) {
    return Rental.builder()
        .member(member)
        .item(item)
        .build();
  }

  @PrePersist
  public void prePersist() {
    setIsExtension(false);
    setReturnDate();
  }

  public void extend() {
    setIsExtension(true);
    setReturnDate();
  }

  public void setReturnDate() {
    setReturnDate(getItem().getReturnDate(getIsExtension()));
  }
}
