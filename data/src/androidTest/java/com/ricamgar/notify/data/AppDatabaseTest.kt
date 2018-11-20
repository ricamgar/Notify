package com.ricamgar.notify.data

import android.arch.persistence.db.framework.FrameworkSQLiteOpenHelperFactory
import android.arch.persistence.room.testing.MigrationTestHelper
import com.ricamgar.notify.data.database.AppDatabase
import org.junit.Rule

class AppDatabaseTest {
  @Rule val testHelper: MigrationTestHelper = MigrationTestHelper(
    InstrumentationRegistry.getInstrumentation(),
    AppDatabase::class.java.canonicalName,
    FrameworkSQLiteOpenHelperFactory()
  )
}