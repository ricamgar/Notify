package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Flowable
import javax.inject.Inject

class GetReminders
@Inject constructor(private val remindersRepository: RemindersRepository) {

  fun execute(history: Boolean): Flowable<List<Reminder>> = when {
    history -> remindersRepository.historyReminders
    else -> remindersRepository.todoReminders
  }
}
