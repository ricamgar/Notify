package com.ricamgar.notify.domain.reminder.model;

import java.io.Serializable;

public final class Reminder implements Serializable {

  public static final Reminder NULL = new Reminder(-1L, "", false, 0);

  public final Long id;
  public final String description;
  public final boolean done;
  public final Integer groupId;

  public Reminder(Long id, String description, boolean done, Integer groupId) {
    this.id = id;
    this.description = description;
    this.done = done;
    this.groupId = groupId;
  }

  Reminder(Builder builder) {
    this(builder.id, builder.description, builder.done, builder.groupId);
  }

  public static final class Builder {
    Long id;
    String description;
    boolean done;
    Integer groupId;

    public Builder id(Long val) {
      id = val;
      return this;
    }

    public Builder description(String val) {
      description = val;
      return this;
    }

    public Builder done(boolean val) {
      done = val;
      return this;
    }

    public Reminder build() {
      return new Reminder(this);
    }
  }
}
