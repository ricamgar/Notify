package com.ricamgar.notify.main;

import android.app.Application;

import net.hockeyapp.android.CrashManager;

public class NotifyApp extends Application {

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

        CrashManager.register(this);
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
