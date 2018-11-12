package com.ricamgar.notify.domain.reminder.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Single
import org.junit.Test

class GetReminderTest {

  private val remindersRepository: RemindersRepository = mock {
    on { getById(0) } doReturn Single.just(RemindersDoubles.REMINDER)
  }
  private val getReminderUseCase = GetReminder(remindersRepository)

  @Test fun `should forward call to repository`() {
    getReminderUseCase.execute(0)
      .test()
      .assertNoErrors()
      .assertValue(RemindersDoubles.REMINDER)
  }
}