package com.ricamgar.notify.domain.reminder;

import com.ricamgar.notify.domain.reminder.model.Reminder;

import java.util.Collections;
import java.util.List;

public final class RemindersDoubles {
    public static final Reminder REMINDER = new Reminder.Builder()
            .id(0)
            .description("description")
            .sticky(true)
            .done(false)
            .build();
    public static final List<Reminder> REMINDERS_LIST = Collections.singletonList(REMINDER);
}
