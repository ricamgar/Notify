package com.ricamgar.notify.main;

import android.content.Context;

import com.ricamgar.notify.addreminder.AddReminderActivity;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;
import com.ricamgar.notify.reminderslist.RemindersListFragment;

import io.reactivex.Scheduler;
import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  Context provideApplicationContext();

  RemindersRepository provideRemindersRepository();

  @Named(value = "main")
  Scheduler provideMainScheduler();

  @Named(value = "io")
  Scheduler provideIOScheduler();

  void inject(NotifyApp notifyApp);

  void inject(RemindersListFragment remindersListActivity);

  void inject(AddReminderActivity addReminderActivity);
}
