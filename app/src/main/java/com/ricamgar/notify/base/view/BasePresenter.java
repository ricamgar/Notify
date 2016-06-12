package com.ricamgar.notify.base.view;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

public abstract class BasePresenter {

    private final CompositeSubscription subscriptions = new CompositeSubscription();

    protected abstract void attachToView(BaseView view);

    protected void detachFromView() {
        subscriptions.clear();
    }

    protected void addSubscription(Subscription subscription) {
        subscriptions.add(subscription);
    }
}
