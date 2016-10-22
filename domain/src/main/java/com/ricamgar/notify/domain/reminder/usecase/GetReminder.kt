package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCase
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository

import java.util.NoSuchElementException

import rx.Observable
import rx.Scheduler

class GetReminder(private val remindersRepository: RemindersRepository, executionThread: Scheduler,
                  postExecutionThread: Scheduler) : UseCase<Reminder>(executionThread, postExecutionThread) {
    private var reminderId: Long = -1

    fun setId(reminderId: Long) {
        this.reminderId = reminderId
    }

    override fun buildUseCaseObservable() = when {
        reminderId < 0 -> Observable.error(NoSuchElementException())
        else -> remindersRepository.getById(reminderId)
    }
}
