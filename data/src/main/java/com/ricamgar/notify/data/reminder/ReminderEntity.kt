package com.ricamgar.notify.data.reminder

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "reminders")
internal data class ReminderEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val description: String,
  val done: Boolean,
  val groupId: Int)