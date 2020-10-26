package com.ricamgar.notify.data.group

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "groups")
internal data class GroupEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String)