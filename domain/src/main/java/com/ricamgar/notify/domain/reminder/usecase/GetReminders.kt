package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCase
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import rx.Scheduler
import javax.inject.Inject
import javax.inject.Named

class GetReminders
@Inject
constructor(private val remindersRepository: RemindersRepository,
            @Named(value = "ioThread") executionThread: Scheduler,
            @Named(value = "mainThread") postExecutionThread: Scheduler)
: UseCase<List<Reminder>>(executionThread, postExecutionThread) {
    private var history: Boolean = false

    override fun buildUseCaseObservable() = when {
        history -> remindersRepository.historyReminders
        else -> remindersRepository.todoReminders
    }

    fun history(history: Boolean): GetReminders {
        this.history = history
        return this
    }
}
