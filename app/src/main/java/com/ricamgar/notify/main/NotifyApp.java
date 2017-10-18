package com.ricamgar.notify.main;

import android.app.Application;

import javax.inject.Inject;
import net.hockeyapp.android.CrashManager;

public class NotifyApp extends Application {

  private static ApplicationComponent applicationComponent;

  @Inject NotificationsController notificationsController;

  @Override
  public void onCreate() {
    super.onCreate();

    applicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();

    applicationComponent.inject(this);

    CrashManager.register(this);
  }

  public static ApplicationComponent getApplicationComponent() {
    return applicationComponent;
  }
}
