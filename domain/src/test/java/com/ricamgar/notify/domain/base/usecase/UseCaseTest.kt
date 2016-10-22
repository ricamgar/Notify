package com.ricamgar.notify.domain.base.usecase

import org.junit.Before
import org.mockito.Mock
import org.mockito.MockitoAnnotations

import rx.Scheduler
import rx.schedulers.Schedulers

abstract class UseCaseTest {

    @Mock lateinit var executionThread: Scheduler
    @Mock lateinit var postExecutionThread: Scheduler

    @Before open fun setUp() {
        MockitoAnnotations.initMocks(this)

        executionThread = Schedulers.immediate()
        postExecutionThread = Schedulers.immediate()
    }
}