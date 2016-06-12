package com.ricamgar.notify.domain.reminder.repository;

import com.ricamgar.notify.domain.reminder.model.Reminder;

import java.util.List;

import rx.Observable;

public interface RemindersRepository {

    Observable<List<Reminder>> getAll();

    Observable<Reminder> getById(long id);

    Observable<Reminder> addOrUpdate(Reminder reminder);

    Observable<Integer> delete(Reminder reminder);

    Observable<List<Reminder>> getTodoReminders();

    Observable<List<Reminder>> getHistoryReminders();
}
