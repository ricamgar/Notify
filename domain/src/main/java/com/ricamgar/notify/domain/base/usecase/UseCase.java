package com.ricamgar.notify.domain.base.usecase;

import io.reactivex.Observable;
import io.reactivex.Scheduler;

public abstract class UseCase<T> {

    private final Scheduler executionThread;
    private final Scheduler postExecutionThread;

    /**
     * Constructor
     *
     * @param executionThread The thread to execute the use case
     * @param postExecutionThread The thread to return the results
     */
    protected UseCase(Scheduler executionThread, Scheduler postExecutionThread) {
        this.executionThread = executionThread;
        this.postExecutionThread = postExecutionThread;
    }

    /**
     * Builds an {@link Observable} which will be used when executing the current {@link UseCase}.
     */
    protected abstract Observable<T> buildUseCaseObservable();

    /**
     * Executes the current use case.
     */
    public Observable<T> execute() {
        return buildUseCaseObservable()
                .subscribeOn(executionThread)
                .observeOn(postExecutionThread);
    }
}
