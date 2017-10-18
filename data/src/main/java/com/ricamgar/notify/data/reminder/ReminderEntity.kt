package com.ricamgar.notify.data.reminder

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "reminders")
data class ReminderEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val description: String,
  val done: Boolean,
  val groupId: Int)