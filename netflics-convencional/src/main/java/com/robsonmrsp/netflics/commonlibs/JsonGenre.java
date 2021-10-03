package com.robsonmrsp.netflics.commonlibs;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JsonGenre implements Serializable {
  private static final long serialVersionUID = 1L;
  private UUID id;
  private String name;
}
