package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCaseTest;
import com.ricamgar.notify.domain.reminder.RemindersDoubles;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class DeleteReminderTest extends UseCaseTest {

    @Mock
    RemindersRepository remindersRepository;

    DeleteReminder deleteReminderUseCase;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        when(remindersRepository.delete(RemindersDoubles.REMINDER)).thenReturn(Observable.just(1));

        deleteReminderUseCase = new DeleteReminder(remindersRepository, executionThread, postExecutionThread);
    }

    @Test
    public void testDeleteReminderSuccess() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        deleteReminderUseCase.setReminder(RemindersDoubles.REMINDER);
        deleteReminderUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(1);
    }

    @Test
    public void testDeleteReminderErrorReminderNotSet() throws Exception {
        TestSubscriber<Integer> testSubscriber = new TestSubscriber<>();
        deleteReminderUseCase.execute(testSubscriber);

        testSubscriber.assertError(IllegalArgumentException.class);
    }
}