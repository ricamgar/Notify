package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCase;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import java.util.NoSuchElementException;

import rx.Observable;
import rx.Scheduler;

public class GetReminder extends UseCase {

    private final RemindersRepository remindersRepository;
    private long reminderId = -1;

    protected GetReminder(RemindersRepository remindersRepository, Scheduler executionThread, Scheduler postExecutionThread) {
        super(executionThread, postExecutionThread);
        this.remindersRepository = remindersRepository;
    }

    public void setId(long reminderId) {
        this.reminderId = reminderId;
    }

    @Override
    protected Observable buildUseCaseObservable() {
        if (reminderId < 0) {
            return Observable.error(new NoSuchElementException());
        }
        return remindersRepository.getById(reminderId);
    }
}
