package com.ricamgar.notify.data.reminder.mapper

import com.ricamgar.notify.data.mapper.Mapper
import com.ricamgar.notify.data.reminder.ReminderEntity
import com.ricamgar.notify.domain.reminder.model.Reminder

class ReminderToEntity : Mapper<Reminder, ReminderEntity> {

  override fun map(reminder: Reminder): ReminderEntity {
    return ReminderEntity(
      reminder.id,
      reminder.description,
      reminder.done,
      reminder.groupId)
  }

}