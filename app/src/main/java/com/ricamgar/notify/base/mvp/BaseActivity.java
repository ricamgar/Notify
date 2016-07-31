package com.ricamgar.notify.base.mvp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public abstract class BaseActivity<ViewModel, Presenter extends AbstractBasePresenter<ViewModel>>
        extends AppCompatActivity implements AbstractBasePresenter.BaseView {

    ViewDelegate<ViewModel, Presenter> viewDelegate = new ViewDelegate<ViewModel, Presenter>() {
        @Override
        protected Presenter initializePresenter() {
            return BaseActivity.this.initializePresenter();
        }

        @Override
        protected ViewModel createViewModel() {
            return BaseActivity.this.createViewModel();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewDelegate.onCreate(this, savedInstanceState);
//        UpdateManager.register(this, BuildConfig.HOCKEY_APP_ID);
    }

    @Override
    protected void onStart() {
        super.onStart();
        viewDelegate.onStart(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        CrashManager.register(this, BuildConfig.HOCKEY_APP_ID);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewDelegate.onSavedInstanceState(outState);
    }

    @Override
    protected void onPause() {
        super.onPause();
//        UpdateManager.unregister();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewDelegate.onDestroy();
    }

    protected abstract ViewModel createViewModel();

    protected abstract Presenter initializePresenter();
}
