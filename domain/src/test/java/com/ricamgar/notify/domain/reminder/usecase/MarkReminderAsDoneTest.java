package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCaseTest;
import com.ricamgar.notify.domain.reminder.RemindersDoubles;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

public class MarkReminderAsDoneTest extends UseCaseTest {

    @Mock
    RemindersRepository remindersRepository;

    MarkReminderAsDone markReminderAsDoneUseCase;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        markReminderAsDoneUseCase = new MarkReminderAsDone(remindersRepository, executionThread, postExecutionThread);
    }

    @Test
    public void testMarkReminderAsDoneSuccess() throws Exception {
        Reminder original = RemindersDoubles.REMINDER;
        Reminder markAsDone = new Reminder(original.id, original.description, original.sticky, true);
        when(remindersRepository.addOrUpdate(any(Reminder.class))).thenReturn(Observable.just(markAsDone));

        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        markReminderAsDoneUseCase.setReminder(original);
        markReminderAsDoneUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(markAsDone);
    }

    @Test
    public void testMarkReminderAsDoneErrorNoReminderSet() throws Exception {
        Reminder original = RemindersDoubles.REMINDER;
        Reminder markAsDone = new Reminder(original.id, original.description, original.sticky, true);
        when(remindersRepository.addOrUpdate(any(Reminder.class))).thenReturn(Observable.just(markAsDone));

        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        markReminderAsDoneUseCase.execute(testSubscriber);

        testSubscriber.assertError(IllegalArgumentException.class);
    }
}