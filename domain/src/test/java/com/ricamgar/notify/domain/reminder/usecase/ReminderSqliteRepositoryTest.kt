package com.ricamgar.notify.domain.reminder.usecase

class ReminderSqliteRepositoryTest {
//TODO convert to test for the implementation
//    private val remindersRepository: RemindersRepository = mock()
//    private val markReminderAsDoneUseCase =  MarkReminderAsDone(remindersRepository)
//
//    @Test fun testMarkReminderAsDoneSuccess() {
//        val original = RemindersDoubles.REMINDER
//        val markAsDone = Reminder(original.id, original.description, true, original.groupId)
//        whenever(remindersRepository.addOrUpdate(any(Reminder::class.java))).thenReturn(Single.just(markAsDone))
//
//        markReminderAsDoneUseCase.execute(original).
//            test()
//            .assertNoErrors()
//        .assertValue(markAsDone)
//    }
//
//    @Test fun testMarkReminderAsDoneErrorNoReminderSet() {
//        val original = RemindersDoubles.REMINDER
//        val markAsDone = Reminder(original.id, original.description, original.sticky, true, group)
//        `when`(remindersRepository.addOrUpdate(any(Reminder::class.java))).thenReturn(Observable.just(markAsDone))
//
//        val testSubscriber = TestSubscriber<Reminder>()
//        markReminderAsDoneUseCase.execute().subscribe(testSubscriber)
//
//        testSubscriber.assertError(IllegalArgumentException::class.java)
//    }
//
//    //
//
//    @Test fun testMarkReminderAsTodoSuccess() {
//        val original = RemindersDoubles.REMINDER
//        val markAsTodo = Reminder(original.id, original.description, original.sticky, false, group)
//        `when`(remindersRepository.addOrUpdate(any(Reminder::class.java))).thenReturn(Observable.just(markAsTodo))
//
//        val testSubscriber = TestSubscriber<Reminder>()
//        markReminderAsTodoUseCase.setReminder(original)
//        markReminderAsTodoUseCase.execute().subscribe(testSubscriber)
//
//        testSubscriber.assertNoErrors()
//        testSubscriber.assertValue(markAsTodo)
//    }
//
//    @Test fun testMarkReminderAsTodoErrorNoReminderSet() {
//        val testSubscriber = TestSubscriber<Reminder>()
//        markReminderAsTodoUseCase.execute().subscribe(testSubscriber)
//
//        testSubscriber.assertError(IllegalArgumentException::class.java)
//    }
//  @Test fun getHistoryRemindersReturnsWithoutError() {
//  getRemindersUseCase.history(true)
//  whenever(remindersRepository.historyReminders).thenReturn(Observable.just(RemindersDoubles.REMINDERS_LIST))
//
//  val testSubscriber = TestSubscriber<List<Reminder>>()
//  getRemindersUseCase.execute().subscribe(testSubscriber)
//
//  testSubscriber.assertNoErrors()
//  testSubscriber.assertValue(RemindersDoubles.REMINDERS_LIST)
//}
//
//  @Test fun getTodoRemindersReturnsWithoutError() {
//    getRemindersUseCase.history(false)
//    whenever(remindersRepository.todoReminders).thenReturn(Observable.just(RemindersDoubles.REMINDERS_LIST))
//
//    val testSubscriber = TestSubscriber<List<Reminder>>()
//    getRemindersUseCase.execute().subscribe(testSubscriber)
//
//    testSubscriber.assertNoErrors()
//    testSubscriber.assertValue(RemindersDoubles.REMINDERS_LIST)
//  }
}