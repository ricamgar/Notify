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

public class MarkReminderAsTodoTest extends UseCaseTest {

    @Mock
    RemindersRepository remindersRepository;

    MarkReminderAsTodo markReminderAsTodoUseCase;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();

        markReminderAsTodoUseCase = new MarkReminderAsTodo(remindersRepository, executionThread, postExecutionThread);
    }

    @Test
    public void testMarkReminderAsTodoSuccess() throws Exception {
        Reminder original = RemindersDoubles.REMINDER;
        Reminder markAsTodo = new Reminder(original.id, original.description, original.sticky, false);
        when(remindersRepository.addOrUpdate(any(Reminder.class))).thenReturn(Observable.just(markAsTodo));

        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        markReminderAsTodoUseCase.setReminder(original);
        markReminderAsTodoUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(markAsTodo);
    }

    @Test
    public void testMarkReminderAsTodoErrorNoReminderSet() throws Exception {
        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        markReminderAsTodoUseCase.execute(testSubscriber);

        testSubscriber.assertError(IllegalArgumentException.class);
    }
}