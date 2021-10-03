package com.robsonmrsp.netflics.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "MOVIE")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie extends AbstractEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private UUID id;

  @Column(name = "TITLE")
  private String title;

  @Column(name = "RELEASE_DATE")
  private LocalDate releaseDate;

  @Column(name = "BUDGET")
  private Double budget;

  @ManyToMany
  @JoinTable(
      name = "MOVIE_ACTOR",
      joinColumns = @JoinColumn(name = "ID_MOVIE", referencedColumnName = "ID"),
      inverseJoinColumns = @JoinColumn(name = "ID_ACTOR", referencedColumnName = "ID"))
  private List<Actor> cast;

  @ManyToOne private Genre genre;
}
