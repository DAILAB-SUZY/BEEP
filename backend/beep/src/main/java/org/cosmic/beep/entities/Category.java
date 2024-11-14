package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Category {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;

  private String name;

  private Long expirationTime;

  @OneToMany(mappedBy = "category")
  private List<Item> items;

}
