package com.ricamgar.notify.data

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase
import androidx.test.platform.app.InstrumentationRegistry
import com.ricamgar.notify.data.database.AppDatabase
import com.ricamgar.notify.data.database.MIGRATION_1_2
import com.ricamgar.notify.data.database.MIGRATION_2_3
import com.zdvdev.kursor.toFormattedString
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test

private const val TEST_DB_NAME = "test_db"

class AppDatabaseTest {
  @Rule val testHelper: MigrationTestHelper = MigrationTestHelper(
    InstrumentationRegistry.getInstrumentation(),
    AppDatabase::class.java.canonicalName,
    FrameworkSQLiteOpenHelperFactory()
  )

  @Test fun shouldPerformRemindersMigrationsCorrectly() {
    val cursor = testHelper.createDatabase(TEST_DB_NAME, 1).use {
      it.insert("reminders", SQLiteDatabase.CONFLICT_FAIL, ContentValues().apply {
        put("id", 1)
        put("description", "description")
        put("done", true)
        put("groupId", 99)
      })
      it.query("SELECT * FROM reminders")
    }

    assertEquals(
      cursor.toFormattedString(),
      testHelper.runMigrationsAndValidate(TEST_DB_NAME, 4, true, MIGRATION_1_2, MIGRATION_2_3).use {
        it.query("SELECT * FROM reminders")
      }.toFormattedString()
    )
  }
}