package com.ricamgar.notify.domain.reminder

import com.ricamgar.notify.domain.reminder.model.Reminder

object RemindersDoubles {
    val REMINDER: Reminder = Reminder.Builder().id(0).description("description").done(false).build()
    val REMINDERS_LIST = listOf(REMINDER)
}
