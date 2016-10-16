package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCase;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class DeleteReminder extends UseCase<Integer> {

    private final RemindersRepository remindersRepository;
    private Reminder reminder;

    @Inject
    protected DeleteReminder(RemindersRepository remindersRepository,
                             @Named(value = "ioThread") Scheduler executionThread,
                             @Named(value = "mainThread") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.remindersRepository = remindersRepository;
    }

    public void setReminder(Reminder reminder) {
        this.reminder = reminder;
    }

    @Override
    protected Observable<Integer> buildUseCaseObservable() {
        if (reminder == null) {
            return Observable.error(new IllegalArgumentException());
        }
        return remindersRepository.delete(reminder);
    }
}
