package com.ricamgar.notify.data.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.migration.Migration


val MIGRATION_1_2: Migration = object : Migration(1, 2) {
  override fun migrate(database: SupportSQLiteDatabase) { }
}

val MIGRATION_2_3: Migration = object : Migration(2, 3) {
  override fun migrate(database: SupportSQLiteDatabase) {
    database.execSQL(
      "CREATE TABLE groups (id INTEGER, name TEXT, PRIMARY KEY(id))"
    )
  }
}