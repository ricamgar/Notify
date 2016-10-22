package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCaseTest
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import rx.Observable
import rx.observers.TestSubscriber

class DeleteReminderTest : UseCaseTest() {

    @Mock lateinit var remindersRepository: RemindersRepository

    lateinit var deleteReminderUseCase: DeleteReminder

    @Before override fun setUp() {
        super.setUp()
        `when`(remindersRepository.delete(RemindersDoubles.REMINDER)).thenReturn(Observable.just(1))

        deleteReminderUseCase = DeleteReminder(remindersRepository, executionThread, postExecutionThread)
    }

    @Test fun testDeleteReminderSuccess() {
        val testSubscriber = TestSubscriber<Int>()
        deleteReminderUseCase.setReminder(RemindersDoubles.REMINDER)
        deleteReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(1)
    }

    @Test fun testDeleteReminderErrorReminderNotSet() {
        val testSubscriber = TestSubscriber<Int>()
        deleteReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertError(IllegalArgumentException::class.java)
    }
}