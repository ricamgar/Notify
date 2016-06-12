package com.ricamgar.notify.data.reminder.mapper;

import android.database.Cursor;

import com.ricamgar.notify.data.database.Db;
import com.ricamgar.notify.data.mapper.Mapper;
import com.ricamgar.notify.data.reminder.ReminderContract;
import com.ricamgar.notify.domain.reminder.model.Reminder;

public class CursorToReminder implements Mapper<Cursor, Reminder> {

    @Override
    public Reminder map(Cursor cursor) {
        Integer id = Db.getInt(cursor, ReminderContract._ID);
        String description = Db.getString(cursor, ReminderContract.DESCRIPTION);
        boolean sticky = Db.getBoolean(cursor, ReminderContract.STICKY);
        boolean done = Db.getBoolean(cursor, ReminderContract.DONE);
        return new Reminder(id, description, sticky, done);
    }
}
