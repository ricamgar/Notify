package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCaseTest
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import rx.Observable
import rx.observers.TestSubscriber

class GetRemindersTest : UseCaseTest() {

    @Mock lateinit var mockRepository: RemindersRepository
    lateinit var getRemindersUseCase: GetReminders

    @Before override fun setUp() {
        super.setUp()
        getRemindersUseCase = GetReminders(mockRepository, executionThread, postExecutionThread)
    }

    @Test fun getHistoryRemindersReturnsWithoutError() {
        getRemindersUseCase.history(true)
        `when`(mockRepository.historyReminders).thenReturn(Observable.just(RemindersDoubles.REMINDERS_LIST))

        val testSubscriber = TestSubscriber<List<Reminder>>()
        getRemindersUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(RemindersDoubles.REMINDERS_LIST)
    }

    @Test fun getTodoRemindersReturnsWithoutError() {
        getRemindersUseCase.history(false)
        `when`(mockRepository.todoReminders).thenReturn(Observable.just(RemindersDoubles.REMINDERS_LIST))

        val testSubscriber = TestSubscriber<List<Reminder>>()
        getRemindersUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(RemindersDoubles.REMINDERS_LIST)
    }
}