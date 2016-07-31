package com.ricamgar.notify.domain.reminder.model;

import java.io.Serializable;

public final class Reminder implements Serializable {

    public static final Reminder NULL = new Reminder(-1, "", false, true);

    public final Integer id;
    public final String description;
    public final boolean sticky;
    public final boolean done;

    public Reminder(Integer id, String description, boolean sticky, boolean done) {
        this.id = id;
        this.description = description;
        this.sticky = sticky;
        this.done = done;
    }

    private Reminder(Builder builder) {
        id = builder.id;
        description = builder.description;
        sticky = builder.sticky;
        done = builder.done;
    }

    public static final class Builder {
        private Integer id;
        private String description;
        private boolean sticky;
        private boolean done;

        public Builder() {
        }

        public Builder id(Integer val) {
            id = val;
            return this;
        }

        public Builder description(String val) {
            description = val;
            return this;
        }

        public Builder sticky(boolean val) {
            sticky = val;
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
