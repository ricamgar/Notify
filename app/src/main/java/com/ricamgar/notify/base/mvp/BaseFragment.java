package com.ricamgar.notify.base.mvp;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public abstract class BaseFragment<ViewModel, Presenter extends AbstractBasePresenter<ViewModel>>
        extends Fragment implements AbstractBasePresenter.BaseView {

    ViewDelegate<ViewModel, Presenter> viewDelegate = new ViewDelegate<ViewModel, Presenter>() {
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
        viewDelegate.onStart(this);
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
