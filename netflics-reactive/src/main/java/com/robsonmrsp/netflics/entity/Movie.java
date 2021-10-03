package com.robsonmrsp.netflics.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Table("MOVIE")
@Builder
@Data
public class Movie extends AbstractEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @Column("id")
  private UUID id;

  @Column("TITLE")
  private String title;

  @Column("RELEASE_DATE")
  private LocalDate releaseDate;

  @Column("BUDGET")
  private Double budget;

  @Transient private List<Actor> cast;

  @Transient private Genre genre;
}
