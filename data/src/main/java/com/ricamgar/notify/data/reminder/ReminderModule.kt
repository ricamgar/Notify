package com.ricamgar.notify.data.reminder

import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ReminderModule {

  @Singleton
  @Binds
  internal abstract fun bindRemindersRepository(reminderSqliteRepository: ReminderSqliteRepository): RemindersRepository
}