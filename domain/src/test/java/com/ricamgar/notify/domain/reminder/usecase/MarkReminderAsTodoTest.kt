package com.ricamgar.notify.domain.reminder.usecase

import com.ricamgar.notify.domain.base.usecase.UseCaseTest
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import org.junit.Before
import org.junit.Test
import org.mockito.Matchers.any
import org.mockito.Mock
import org.mockito.Mockito.`when`
import rx.Observable
import rx.observers.TestSubscriber

class MarkReminderAsTodoTest : UseCaseTest() {

    @Mock lateinit var remindersRepository: RemindersRepository
    lateinit var markReminderAsTodoUseCase: MarkReminderAsTodo

    @Before override fun setUp() {
        super.setUp()

        markReminderAsTodoUseCase = MarkReminderAsTodo(remindersRepository, executionThread, postExecutionThread)
    }

    @Test fun testMarkReminderAsTodoSuccess() {
        val original = RemindersDoubles.REMINDER
        val markAsTodo = Reminder(original.id, original.description, original.sticky, false, group)
        `when`(remindersRepository.addOrUpdate(any(Reminder::class.java))).thenReturn(Observable.just(markAsTodo))

        val testSubscriber = TestSubscriber<Reminder>()
        markReminderAsTodoUseCase.setReminder(original)
        markReminderAsTodoUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertNoErrors()
        testSubscriber.assertValue(markAsTodo)
    }

    @Test fun testMarkReminderAsTodoErrorNoReminderSet() {
        val testSubscriber = TestSubscriber<Reminder>()
        markReminderAsTodoUseCase.execute().subscribe(testSubscriber)

        testSubscriber.assertError(IllegalArgumentException::class.java)
    }
}