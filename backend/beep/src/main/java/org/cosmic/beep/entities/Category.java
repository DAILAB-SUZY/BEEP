package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;

  private String name;

  private Long maximumRental;

  private Long expirationTime;

  @OneToMany(mappedBy = "category")
  private List<Item> items;

}
