package com.ricamgar.notify.data.group

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "groups")
internal data class GroupEntity(
  @PrimaryKey(autoGenerate = true)
  val id: Long,
  val name: String)