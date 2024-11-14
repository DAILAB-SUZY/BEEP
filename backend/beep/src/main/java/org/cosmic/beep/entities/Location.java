package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Location {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;

  private String name;
  private String description;

  @OneToMany(mappedBy = "location")
  private List<Item> items;
}
