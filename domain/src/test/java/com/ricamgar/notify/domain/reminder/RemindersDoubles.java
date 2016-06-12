package com.ricamgar.notify.domain.reminder;

import com.ricamgar.notify.domain.reminder.model.Reminder;

import java.util.Collections;
import java.util.List;

public class RemindersDoubles {
    public static final Reminder REMINDER = new Reminder(0, "description", true, false);
    public static final List<Reminder> REMINDERS_LIST = Collections.singletonList(RemindersDoubles.REMINDER);
}
