package com.ricamgar.notify.domain.base.usecase;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.schedulers.Schedulers;

public abstract class UseCaseTest {

    @Mock
    protected Scheduler executionThread;
    @Mock
    protected Scheduler postExecutionThread;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        executionThread = Schedulers.immediate();
        postExecutionThread = Schedulers.immediate();
    }
}