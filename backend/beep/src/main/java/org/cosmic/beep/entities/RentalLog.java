package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import java.time.Instant;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class RentalLog {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;

  @ManyToOne
  @JoinColumn(name = "member_id")
  private Member member;

  @ManyToOne
  @JoinColumn(name = "item_id")
  private Item item;

  private Instant rentDate;

  private Instant returnDate;

  @PrePersist
  public void perPersist() {
    setRentDate(Instant.now());
  }
}
