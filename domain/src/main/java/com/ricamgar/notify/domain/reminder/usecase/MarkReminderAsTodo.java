package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCase;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class MarkReminderAsTodo extends UseCase<Reminder> {

    private final RemindersRepository remindersRepository;
    private Reminder reminder;

    @Inject
    protected MarkReminderAsTodo(RemindersRepository remindersRepository,
                                 @Named(value = "ioThread") Scheduler executionThread,
                                 @Named(value = "mainThread") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.remindersRepository = remindersRepository;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    @Override
    protected Observable<Reminder> buildUseCaseObservable() {
        if (reminder == null) {
            return Observable.error(new IllegalArgumentException());
        }

        Reminder todoReminder = new Reminder(reminder.id, reminder.description, reminder.sticky, false);
        return remindersRepository.addOrUpdate(todoReminder);

    }
}
