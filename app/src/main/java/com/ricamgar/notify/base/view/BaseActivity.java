package com.ricamgar.notify.base.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.ricamgar.notify.BuildConfig;

import net.hockeyapp.android.CrashManager;
import net.hockeyapp.android.UpdateManager;

public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        UpdateManager.register(this, BuildConfig.HOCKEY_APP_ID);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        CrashManager.register(this, BuildConfig.HOCKEY_APP_ID);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        UpdateManager.unregister();
    }
}
