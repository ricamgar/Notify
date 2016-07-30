package com.ricamgar.notify.base.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public abstract class BaseFragment<ViewModel, View extends BaseView, Presenter extends AbstractBasePresenter<View,
        ViewModel>>  extends Fragment implements BaseView {

    ViewDelegate<ViewModel, View, Presenter> viewDelegate = new ViewDelegate<ViewModel, View, Presenter>() {
        @Override
        protected Presenter initializePresenter() {
            return BaseFragment.this.initializePresenter();
        }

        @Override
        protected ViewModel createViewModel() {
            return BaseFragment.this.createViewModel();
        }
    };

    @Override
    public void onViewCreated(android.view.View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewDelegate.onCreate(this, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        viewDelegate.onStart((View) this);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        viewDelegate.onSavedInstanceState(outState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewDelegate.onDestroy();
    }

    protected abstract ViewModel createViewModel();

    protected abstract Presenter initializePresenter();
}
