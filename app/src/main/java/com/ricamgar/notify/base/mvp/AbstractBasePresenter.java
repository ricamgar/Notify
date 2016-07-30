package com.ricamgar.notify.base.mvp;

/**
 * @author ricardo.campos.
 */

import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/** Abstraction for the Presenters in a MVP pattern. */
public abstract class AbstractBasePresenter<View extends BaseView, Model> {

    protected View view;
    protected Model viewModel;

    private final CompositeSubscription compositeSubscription = new CompositeSubscription();

    public void attachToView(View view, @Nullable Model viewModel) {
        this.view = view;
        this.viewModel = viewModel;
    }

    /**
     * Method that control the lifecycle of the view.
     * It should be called in the view's (Activity or Fragment) onDestroy() method.
     */
    @CallSuper
    void destroy() {
        view = null;
        viewModel = null;
        compositeSubscription.clear();
    }

    /**
     * Add a subscription to a {@link CompositeSubscription} to be handled with the lifecycle
     *
     * @param subscription The {@link Subscription} to add
     */
    protected void addSubscription(Subscription subscription) {
        compositeSubscription.add(subscription);
    }
}
