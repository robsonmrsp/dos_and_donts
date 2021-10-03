package com.robsonmrsp.netflics.entity;

import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@Table(name = "ACTOR")
@NoArgsConstructor
@AllArgsConstructor
public class Actor extends AbstractEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private UUID id;

  @Column(name = "NAME")
  private String name;

  @Column(name = "BIRTH_DATE")
  private String birthDate;

  @ManyToMany(mappedBy = "cast")
  private List<Movie> work;
}
