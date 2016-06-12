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

import static org.mockito.Mockito.when;

public class AddOrUpdateReminderTest extends UseCaseTest {

    @Mock
    RemindersRepository remindersRepository;

    AddOrUpdateReminder addOrUpdateReminderUseCase;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        addOrUpdateReminderUseCase = new AddOrUpdateReminder(remindersRepository, executionThread, postExecutionThread);

        when(remindersRepository.addOrUpdate(RemindersDoubles.REMINDER)).thenReturn(Observable.just(RemindersDoubles.REMINDER));
    }

    @Test
    public void testAddReminderSuccess() throws Exception {
        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        addOrUpdateReminderUseCase.setReminder(RemindersDoubles.REMINDER);
        addOrUpdateReminderUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(RemindersDoubles.REMINDER);
    }

    @Test
    public void testAddReminderErrorReminderNotSet() throws Exception {
        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        addOrUpdateReminderUseCase.execute(testSubscriber);

        testSubscriber.assertError(IllegalArgumentException.class);
    }
}