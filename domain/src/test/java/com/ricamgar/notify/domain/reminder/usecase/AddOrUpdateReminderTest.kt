package com.ricamgar.notify.domain.reminder.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Single
import org.junit.Test

class AddOrUpdateReminderTest {

  private val remindersRepository: RemindersRepository = mock {
    on { addOrUpdate(RemindersDoubles.REMINDER) } doReturn Single.just(RemindersDoubles.REMINDER)
  }
  private val addOrUpdateReminderUseCase = AddOrUpdateReminder(remindersRepository)

  @Test fun `should forward call to repository`() {
    addOrUpdateReminderUseCase.execute(RemindersDoubles.REMINDER)
      .test()
      .assertNoErrors()
      .assertValue(RemindersDoubles.REMINDER)
  }
}