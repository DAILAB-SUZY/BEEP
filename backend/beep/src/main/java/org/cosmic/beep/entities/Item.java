package org.cosmic.beep.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Item {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private String id;
  private String name;
  private String description;

  @ManyToOne
  @JoinColumn(name = "category_id")
  private Category category;

  @ManyToOne
  @JoinColumn(name = "location_id")
  private Location location;
}
