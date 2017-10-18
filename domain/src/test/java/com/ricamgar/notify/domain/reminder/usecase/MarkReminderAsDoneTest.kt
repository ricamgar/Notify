package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCaseTest
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository

import org.junit.Before
import org.junit.Test
import org.mockito.Mock

import rx.Observable
import rx.observers.TestSubscriber

import org.mockito.Matchers.any
import org.mockito.Mockito.`when`

class MarkReminderAsDoneTest : UseCaseTest() {

    @Mock lateinit var remindersRepository: RemindersRepository
    lateinit var markReminderAsDoneUseCase: MarkReminderAsDone

    @Before override fun setUp() {
        super.setUp()

        markReminderAsDoneUseCase = MarkReminderAsDone(remindersRepository, executionThread, postExecutionThread)
    }

    @Test fun testMarkReminderAsDoneSuccess() {
        val original = RemindersDoubles.REMINDER
        val markAsDone = Reminder(original.id, original.description, original.sticky, true, group)
        `when`(remindersRepository.addOrUpdate(any(Reminder::class.java))).thenReturn(Observable.just(markAsDone))

        val testSubscriber = TestSubscriber<Reminder>()
        markReminderAsDoneUseCase.setReminder(original)
        markReminderAsDoneUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(markAsDone)
    }

    @Test fun testMarkReminderAsDoneErrorNoReminderSet() {
        val original = RemindersDoubles.REMINDER
        val markAsDone = Reminder(original.id, original.description, original.sticky, true, group)
        `when`(remindersRepository.addOrUpdate(any(Reminder::class.java))).thenReturn(Observable.just(markAsDone))

        val testSubscriber = TestSubscriber<Reminder>()
        markReminderAsDoneUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertError(IllegalArgumentException::class.java)
    }
}