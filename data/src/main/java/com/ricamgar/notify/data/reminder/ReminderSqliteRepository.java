package com.ricamgar.notify.data.reminder;

import android.database.sqlite.SQLiteDatabase;

import com.ricamgar.notify.data.reminder.mapper.CursorToReminder;
import com.ricamgar.notify.data.reminder.mapper.ReminderToValues;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;
import com.squareup.sqlbrite.BriteDatabase;

import java.util.List;

import rx.Observable;

public class ReminderSqliteRepository implements RemindersRepository {

    private final BriteDatabase database;
    private final CursorToReminder cursorToReminderMapper;
    private final ReminderToValues reminderToValuesMapper;

    public ReminderSqliteRepository(BriteDatabase database, CursorToReminder cursorToReminderMapper,
                                    ReminderToValues reminderToValuesMapper) {
        this.database = database;
        this.cursorToReminderMapper = cursorToReminderMapper;
        this.reminderToValuesMapper = reminderToValuesMapper;
    }

    @Override
    public Observable<List<Reminder>> getAll() {
        return database.createQuery(ReminderContract.TABLE, "SELECT * FROM " + ReminderContract.TABLE +
                " ORDER BY 1 DESC")
                .mapToList(cursorToReminderMapper::map);
    }

    @Override
    public Observable<Reminder> getById(long id) {
        return database.createQuery(
                ReminderContract.TABLE,
                "SELECT * FROM " + ReminderContract.TABLE + " WHERE " + ReminderContract._ID + " = ?",
                String.valueOf(id))
                .mapToOneOrDefault(cursorToReminderMapper::map, Reminder.NULL);
    }

    @Override
    public Observable<Reminder> addOrUpdate(Reminder reminder) {
        return Observable.fromCallable(() -> {
            long rowId = database.insert(ReminderContract.TABLE, reminderToValuesMapper.map(reminder), SQLiteDatabase.CONFLICT_REPLACE);
            if (rowId != -1) {
                return reminder;
            } else {
                throw new IllegalStateException("Error while trying to insert reminder into database");
            }
        });
    }

    @Override
    public Observable<Integer> delete(Reminder reminder) {
        return Observable.fromCallable(() -> {
            int rows = database.delete(ReminderContract.TABLE, ReminderContract._ID + "=?",
                    String.valueOf(reminder.id));
            if (rows > 0) {
                return rows;
            } else {
                throw new IllegalStateException("The row to delete was not found");
            }
        });
    }

    @Override
    public Observable<List<Reminder>> getTodoReminders() {
        return database.createQuery(
                ReminderContract.TABLE,
                "SELECT * FROM " + ReminderContract.TABLE +
                        " WHERE " + ReminderContract.DONE + " = 0" +
                        " ORDER BY 1 DESC")
                .mapToList(cursorToReminderMapper::map);
    }

    @Override
    public Observable<List<Reminder>> getHistoryReminders() {
        return database.createQuery(
                ReminderContract.TABLE,
                "SELECT * FROM " + ReminderContract.TABLE +
                        " WHERE " + ReminderContract.DONE + " = 1" +
                        " ORDER BY 1 DESC")
                .mapToList(cursorToReminderMapper::map);
    }
}
