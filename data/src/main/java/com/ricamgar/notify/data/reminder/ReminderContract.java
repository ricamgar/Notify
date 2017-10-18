package com.ricamgar.notify.data.reminder;

import android.provider.BaseColumns;

public final class ReminderContract implements BaseColumns {
  static final String TABLE = "reminder";

  public static final String DESCRIPTION = "description";
  public static final String STICKY = "sticky";
  public static final String DONE = "done";
  public static final String GROUP = "groupId";

  public static final String CREATE_REMINDER_TABLE_SQL = ""
      + "CREATE TABLE " + ReminderContract.TABLE + "("
      + ReminderContract._ID + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,"
      + ReminderContract.DESCRIPTION + " TEXT NOT NULL,"
      + ReminderContract.DONE + " INTEGER NOT NULL DEFAULT 1,"
      + ReminderContract.STICKY + " INTEGER NOT NULL DEFAULT 0 "
      + ReminderContract.GROUP + "INTEGER NOT NULL DEFAULT 0 "
      +")";
}
