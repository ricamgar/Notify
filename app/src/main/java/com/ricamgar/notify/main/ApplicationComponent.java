package com.ricamgar.notify.main;

import android.content.Context;

import com.ricamgar.notify.addreminder.AddReminderActivity;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;
import com.ricamgar.notify.reminderslist.RemindersListFragment;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Component;
import rx.Scheduler;

@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

    void inject(RemindersListFragment remindersListActivity);
    void inject(AddReminderActivity addReminderActivity);
    void inject(NotificationsController notificationsController);

    Context provideApplicationContext();

    RemindersRepository provideRemindersRepository();

    @Named(value = "mainThread")
    Scheduler provideMainScheduler();

    @Named(value = "ioThread")
    Scheduler provideIOScheduler();

}
