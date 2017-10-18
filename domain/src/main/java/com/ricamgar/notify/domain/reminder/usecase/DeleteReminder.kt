package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Completable
import javax.inject.Inject

class DeleteReminder
@Inject constructor(private val remindersRepository: RemindersRepository) {

  fun execute(id: Long): Completable = remindersRepository.delete(id)
}
