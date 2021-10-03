package com.robsonmrsp.netflics.entity;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
  private static final long serialVersionUID = -7964355524118760783L;

  @Column(name = "CREATE_DATETIME")
  private LocalDateTime createDatetime;

  @Column(name = "LAST_UPDATE_DATETIME")
  private LocalDateTime lastUpdateDatetime;

  @Column(name = "USER_CREATE")
  private String userCreate;

  @Column(name = "USER_CHANGE")
  private String userChange;
}
