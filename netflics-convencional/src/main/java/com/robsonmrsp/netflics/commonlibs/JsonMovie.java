package com.robsonmrsp.netflics.commonlibs;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonMovie implements Serializable {

  private static final long serialVersionUID = 1L;

  private UUID id;

  private String title;

  private LocalDate releaseDate;

  private Double budget;

  private List<JsonActor> cast;

  private JsonGenre genre;
}
