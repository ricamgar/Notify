package com.ricamgar.notify.main;

import android.app.Application;
import android.content.Context;

import com.ricamgar.notify.data.database.DbOpenHelper;
import com.ricamgar.notify.data.reminder.ReminderSqliteRepository;
import com.ricamgar.notify.data.reminder.mapper.CursorToReminder;
import com.ricamgar.notify.data.reminder.mapper.ReminderToValues;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;
import com.squareup.sqlbrite.BriteDatabase;
import com.squareup.sqlbrite.SqlBrite;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

@Module
public class ApplicationModule {
    private final Application application;
    private final RemindersRepository remindersRepository;

    ApplicationModule(Application application) {
        this.application = application;
        remindersRepository = createRemindersRepository();
        new NotificationsController(application, remindersRepository);
    }

    private RemindersRepository createRemindersRepository() {
        SqlBrite sqlBrite = SqlBrite.create();
        DbOpenHelper dbOpenHelper = new DbOpenHelper(application);
        BriteDatabase briteDatabase = sqlBrite.wrapDatabaseHelper(dbOpenHelper, AndroidSchedulers.mainThread());
        return new ReminderSqliteRepository(briteDatabase, new CursorToReminder(), new ReminderToValues());
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
    @Named(value = "mainThread")
    Scheduler provideMainScheduler() {
        return AndroidSchedulers.mainThread();
    }

    @Singleton
    @Provides
    @Named(value = "ioThread")
    Scheduler provideIOScheduler() {
        return Schedulers.io();
    }
}
