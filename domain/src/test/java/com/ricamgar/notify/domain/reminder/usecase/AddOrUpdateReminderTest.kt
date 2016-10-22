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

class AddOrUpdateReminderTest : UseCaseTest() {

    @Mock lateinit var remindersRepository: RemindersRepository

    lateinit var addOrUpdateReminderUseCase: AddOrUpdateReminder

    @Before override fun setUp() {
        super.setUp()
        addOrUpdateReminderUseCase = AddOrUpdateReminder(remindersRepository, executionThread, postExecutionThread)

        `when`(remindersRepository.addOrUpdate(RemindersDoubles.REMINDER)).thenReturn(Observable.just(RemindersDoubles.REMINDER))
    }

    @Test fun testAddReminderSuccess() {
        val testSubscriber = TestSubscriber<Reminder>()
        addOrUpdateReminderUseCase.setReminder(RemindersDoubles.REMINDER)
        addOrUpdateReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(RemindersDoubles.REMINDER)
    }

    @Test fun testAddReminderErrorReminderNotSet() {
        val testSubscriber = TestSubscriber<Reminder>()
        addOrUpdateReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertError(IllegalArgumentException::class.java)
    }
}