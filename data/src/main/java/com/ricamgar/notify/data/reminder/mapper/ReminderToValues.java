package com.ricamgar.notify.data.reminder.mapper;

import android.content.ContentValues;

import com.ricamgar.notify.data.mapper.Mapper;
import com.ricamgar.notify.data.reminder.ReminderContract;
import com.ricamgar.notify.domain.reminder.model.Reminder;

public class ReminderToValues implements Mapper<Reminder, ContentValues> {

    @Override
    public ContentValues map(Reminder reminder) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(ReminderContract._ID, reminder.id);
        contentValues.put(ReminderContract.DESCRIPTION, reminder.description);
        contentValues.put(ReminderContract.STICKY, reminder.sticky);
        contentValues.put(ReminderContract.DONE, reminder.done);
        return contentValues;
    }
}
