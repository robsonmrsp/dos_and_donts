package com.robsonmrsp.netflics.entity;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Table(name = "GENRE")
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Genre extends AbstractEntity {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "id", updatable = false, unique = true, nullable = false)
  private UUID id;

  @Column(name = "NAME")
  private String name;
}
