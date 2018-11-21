package com.ricamgar.notify.domain.reminder.usecase

import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.ricamgar.notify.domain.reminder.RemindersDoubles
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Completable
import org.junit.Test

class DeleteReminderTest {

  private val remindersRepository: RemindersRepository = mock {
    on { delete(RemindersDoubles.REMINDER.id) } doReturn Completable.complete()
  }
  private val deleteReminderUseCase: DeleteReminder = DeleteReminder(remindersRepository)

  @Test fun `should forward call to repository`() {
    deleteReminderUseCase.execute(RemindersDoubles.REMINDER.id)
      .test()
      .assertNoErrors()
      .assertComplete()
  }
}