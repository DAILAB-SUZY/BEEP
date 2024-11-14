package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
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
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;

  private Long maximumRental;

  private Long expirationTime;

  @OneToMany(mappedBy = "category")
  @Builder.Default
  private List<Item> items = new ArrayList<>();

  public static Category from(String name, Long maximumRental, Long expirationTime) {
    return Category.builder()
        .name(name)
        .maximumRental(maximumRental)
        .expirationTime(expirationTime)
        .build();
  }
}
