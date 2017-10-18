package com.ricamgar.notify.main;

import android.app.Application;
import android.content.Context;

import com.ricamgar.notify.data.database.AppDatabase;
import com.ricamgar.notify.data.reminder.ReminderSqliteRepository;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
public class ApplicationModule {
  private final Application application;
  private final RemindersRepository remindersRepository;

  ApplicationModule(Application application) {
    this.application = application;
    remindersRepository = createRemindersRepository();
  }

  private RemindersRepository createRemindersRepository() {
    AppDatabase briteDatabase = AppDatabase.Companion.createPersistentDatabase(application);
    return new ReminderSqliteRepository(briteDatabase.reminderModel());
  }

  @Singleton
  @Provides
  NotificationsController provideNotificationsController(Context context, RemindersRepository remindersRepository,
      @Named("io")Scheduler ioThread) {
    return new NotificationsController(context, remindersRepository, ioThread);
  }

  @Singleton
  @Provides
  Context provideApplication() {
    return application.getApplicationContext();
  }

  @Singleton
  @Provides
  RemindersRepository provideRemindersRepository() {
    return remindersRepository;
  }

  @Singleton
  @Provides
  @Named(value = "main")
  Scheduler provideMainScheduler() {
    return AndroidSchedulers.mainThread();
  }

  @Singleton
  @Provides
  @Named(value = "io")
  Scheduler provideIOScheduler() {
    return Schedulers.io();
  }
}
