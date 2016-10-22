package com.ricamgar.notify.domain.reminder

import com.ricamgar.notify.domain.reminder.model.Reminder

object RemindersDoubles {
    val REMINDER = Reminder.Builder().id(0).description("description").sticky(true).done(false).build()
    val REMINDERS_LIST = listOf(REMINDER)
}
