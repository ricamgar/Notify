package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Single
import javax.inject.Inject

class MarkReminderAsTodo
@Inject constructor(private val remindersRepository: RemindersRepository) {

    fun execute(reminder: Reminder): Single<Reminder> =
      remindersRepository.addOrUpdate(reminder)
}
