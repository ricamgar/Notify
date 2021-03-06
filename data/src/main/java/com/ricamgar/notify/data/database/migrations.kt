package com.ricamgar.notify.data.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase


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