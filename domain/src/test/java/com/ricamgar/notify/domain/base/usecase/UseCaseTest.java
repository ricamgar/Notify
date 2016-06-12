package com.ricamgar.notify.domain.base.usecase;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Scheduler;
import rx.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public abstract class UseCaseTest {

    @Mock
    protected Scheduler executionThread;
    @Mock
    protected Scheduler postExecutionThread;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        when(executionThread).thenReturn(Schedulers.immediate());
        when(postExecutionThread).thenReturn(Schedulers.immediate());
    }
}