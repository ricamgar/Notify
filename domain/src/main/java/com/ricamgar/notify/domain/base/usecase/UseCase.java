package com.ricamgar.notify.domain.base.usecase;

import rx.Observable;
import rx.Scheduler;
import rx.Subscriber;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
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
