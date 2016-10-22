package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCase
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import rx.Observable
import rx.Scheduler
import javax.inject.Inject
import javax.inject.Named

class AddOrUpdateReminder
@Inject
constructor(private val remindersRepository: RemindersRepository,
            @Named(value = "ioThread") executionThread: Scheduler,
            @Named(value = "mainThread") postExecutionThread: Scheduler)
                : UseCase<Reminder>(executionThread, postExecutionThread) {
    private var reminder: Reminder? = null

    fun setReminder(reminder: Reminder): AddOrUpdateReminder {
        this.reminder = reminder
        return this
    }

    override fun buildUseCaseObservable() = when (reminder) {
        null -> Observable.error(IllegalArgumentException())
        else -> remindersRepository.addOrUpdate(reminder)
    }
}
