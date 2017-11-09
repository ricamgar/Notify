package com.ricamgar.notify.data.reminder

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.ricamgar.notify.domain.reminder.model.Reminder
import io.reactivex.Flowable

@Dao
internal abstract class ReminderDao {

  @Query("SELECT id, description, done, groupId FROM reminders WHERE id = :id")
  abstract fun reminder(id: Long): Reminder

  @Query("SELECT id, description, done, groupId FROM reminders WHERE done = 0 ORDER BY 1 DESC")
  abstract fun todoReminders(): Flowable<List<Reminder>>

  @Query("SELECT id, description, done, groupId FROM reminders WHERE done = 1 ORDER BY 1 DESC")
  abstract fun historyReminders(): Flowable<List<Reminder>>

  @Insert(onConflict = OnConflictStrategy.REPLACE)
  abstract fun add(reminderEntity: ReminderEntity): Long

  @Query("DELETE FROM reminders WHERE id = :id")
  abstract fun delete(id: Long)
}