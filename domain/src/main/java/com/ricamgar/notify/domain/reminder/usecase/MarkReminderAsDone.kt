package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCase
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository

import javax.inject.Inject
import javax.inject.Named

import rx.Observable
import rx.Scheduler

class MarkReminderAsDone
@Inject
constructor(private val remindersRepository: RemindersRepository,
            @Named(value = "ioThread") executionThread: Scheduler,
            @Named(value = "mainThread") postExecutionThread: Scheduler)
                : UseCase<Reminder>(executionThread, postExecutionThread) {
    private var reminder: Reminder? = null

    fun setReminder(reminder: Reminder) {
        this.reminder = reminder
    }

    override fun buildUseCaseObservable() = when (reminder) {
        null -> Observable.error<Reminder>(IllegalArgumentException())
        else -> {
            val doneReminder = Reminder(reminder!!.id, reminder!!.description, reminder!!.sticky, true)
            remindersRepository.addOrUpdate(doneReminder)
        }
    }
}
