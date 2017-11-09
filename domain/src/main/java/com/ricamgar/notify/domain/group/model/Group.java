package com.ricamgar.notify.domain.group.model;

import java.io.Serializable;

public final class Group implements Serializable {

  public final Long id;
  public final String name;

  public Group(Long id, String name) {
    this.id = id;
    this.name = name;
  }
}
