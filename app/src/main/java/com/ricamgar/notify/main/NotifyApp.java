package com.ricamgar.notify.main;

import android.app.Application;
import android.util.Log;

public class NotifyApp extends Application {

    private static final String TAG = NotifyApp.class.getSimpleName();

    private static ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public static ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
