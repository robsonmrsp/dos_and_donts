package com.robsonmrsp.netflics.commonlibs;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor

public class JsonActor implements Serializable {
  private static final long serialVersionUID = 1L;

  private UUID id;

  private String name;

  private String birthDate;

  private ArrayList<JsonMovie> work = new ArrayList<JsonMovie>();
}
