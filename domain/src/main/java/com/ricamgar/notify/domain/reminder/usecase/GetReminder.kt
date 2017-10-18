package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Single
import javax.inject.Inject

class GetReminder
@Inject constructor(private val remindersRepository: RemindersRepository) {

  fun execute(reminderId: Long): Single<Reminder> = remindersRepository.getById(reminderId)
}
