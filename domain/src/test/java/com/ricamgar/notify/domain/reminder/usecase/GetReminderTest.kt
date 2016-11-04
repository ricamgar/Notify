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
import java.util.*

class GetReminderTest : UseCaseTest() {

    @Mock lateinit var remindersRepository: RemindersRepository

    lateinit var getReminderUseCase: GetReminder

    @Before override fun setUp() {
        super.setUp()
        getReminderUseCase = GetReminder(remindersRepository, executionThread, postExecutionThread)

        `when`(remindersRepository.getById(0)).thenReturn(Observable.just(RemindersDoubles.REMINDER))
        `when`(remindersRepository.getById(1)).thenReturn(Observable.empty<Reminder>())
    }

    @Test fun testGetReminderByIdSuccess() {
        val testSubscriber = TestSubscriber<Reminder>()
        getReminderUseCase.setId(0)
        getReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(RemindersDoubles.REMINDER)
    }

    @Test fun testGetReminderByIdNotFound() {
        val testSubscriber = TestSubscriber<Reminder>()
        getReminderUseCase.setId(1)
        getReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertNoValues()
    }

    @Test fun testGetReminderByIdErrorIdNotSet() {
        val testSubscriber = TestSubscriber<Reminder>()
        getReminderUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertError(NoSuchElementException::class.java)
    }
}