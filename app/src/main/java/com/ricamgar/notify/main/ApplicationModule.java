package com.ricamgar.notify.main;

import android.app.Application;
import android.content.Context;

import com.ricamgar.notify.data.database.AppDatabase;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;
import dagger.Module;
import dagger.Provides;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import javax.inject.Named;
import javax.inject.Singleton;

@Module
class ApplicationModule {

  private final Application application;

  ApplicationModule(Application application) {
    this.application = application;
  }

  @Singleton
  @Provides
  AppDatabase provideAppDatabase() {
    return AppDatabase.Companion.createPersistentDatabase(application);
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
