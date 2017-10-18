package com.ricamgar.notify.data.database

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ricamgar.notify.data.reminder.ReminderDao
import com.ricamgar.notify.data.reminder.ReminderEntity

@Database(
  entities = arrayOf(ReminderEntity::class),
  version = 2
)
abstract class AppDatabase : RoomDatabase() {

  abstract fun reminderModel(): ReminderDao

  companion object {
    private const val DB_NAME = "notify.db"

    fun createInMemoryDatabase(context: Context): AppDatabase
      = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java).build()

    fun createPersistentDatabase(context: Context): AppDatabase
      = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)
      .build()
  }

}