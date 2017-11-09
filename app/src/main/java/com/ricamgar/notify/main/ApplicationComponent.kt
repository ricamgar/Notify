package com.ricamgar.notify.main

import android.content.Context

import com.ricamgar.notify.addreminder.AddReminderActivity
import com.ricamgar.notify.data.group.GroupModule
import com.ricamgar.notify.data.reminder.ReminderModule
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import com.ricamgar.notify.reminderslist.RemindersListFragment

import io.reactivex.Scheduler
import javax.inject.Named
import javax.inject.Singleton

import dagger.Component

@Singleton
@Component(modules = arrayOf(ApplicationModule::class, ReminderModule::class, GroupModule::class))
interface ApplicationComponent {

  fun provideApplicationContext(): Context

  fun provideRemindersRepository(): RemindersRepository

  @Named(value = "main")
  fun provideMainScheduler(): Scheduler

  @Named(value = "io")
  fun provideIOScheduler(): Scheduler

  fun inject(notifyApp: NotifyApp)

  fun inject(remindersListActivity: RemindersListFragment)

  fun inject(addReminderActivity: AddReminderActivity)
}
