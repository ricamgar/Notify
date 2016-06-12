package com.ricamgar.notify.data.database;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ricamgar.notify.data.reminder.ReminderContract;

public final class DbOpenHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    public DbOpenHelper(Context context) {
        super(context, "notify.db", null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(ReminderContract.CREATE_REMINDER_TABLE_SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private void deleteAllData(Application application) {
        getWritableDatabase().delete(ReminderContract.TABLE, null, null);
//        NotificationsCenter.deleteAllNotifications(application);
    }
}
