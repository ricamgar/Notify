package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCase;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import rx.Observable;
import rx.Scheduler;

public class GetReminders extends UseCase<List<Reminder>> {

    private final RemindersRepository remindersRepository;
    private boolean history;

    @Inject
    protected GetReminders(RemindersRepository remindersRepository,
                           @Named(value = "ioThread") Scheduler executionThread,
                           @Named(value = "mainThread") Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.remindersRepository = remindersRepository;
    }

    @Override
    protected Observable<List<Reminder>> buildUseCaseObservable() {
        if (history) {
            return remindersRepository.getHistoryReminders();
        } else {
            return remindersRepository.getTodoReminders();
        }
    }

    public GetReminders history(boolean history) {
        this.history = history;
        return this;
    }
}
