package com.robsonmrsp.netflics.commonlibs;

import java.io.Serializable;
import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilterMovie implements Serializable {

  private String id;

  private String title;

  private LocalDate releaseDate;

  private String genre;
}
