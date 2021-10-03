package com.robsonmrsp.netflics.entity;

import java.util.List;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Table("ACTOR")
public class Actor extends AbstractEntity {
  private static final long serialVersionUID = 1L;

  @Id private UUID id;

  @Column("NAME")
  private String name;

  @Column("BIRTH_DATE")
  private String birthDate;

  @Transient private List<Movie> work;
}
