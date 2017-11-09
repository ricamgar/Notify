package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Single
import javax.inject.Inject

class MarkReminderAsDone
@Inject constructor(
  private val remindersRepository: RemindersRepository
) {

  fun execute(reminder: Reminder): Single<Reminder> {
    val doneReminder = Reminder(reminder.id, reminder.description, true, reminder.groupId)
    return remindersRepository.addOrUpdate(doneReminder)
  }
}
