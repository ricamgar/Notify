package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCaseTest;
import com.ricamgar.notify.domain.reminder.RemindersDoubles;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.NoSuchElementException;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class GetReminderTest extends UseCaseTest {

    @Mock
    RemindersRepository remindersRepository;

    GetReminder getReminderUseCase;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        getReminderUseCase = new GetReminder(remindersRepository, executionThread, postExecutionThread);

        when(remindersRepository.getById(0)).thenReturn(Observable.just(RemindersDoubles.REMINDER));
        when(remindersRepository.getById(1)).thenReturn(Observable.empty());
    }

    @Test
    public void testGetReminderByIdSuccess() throws Exception {
        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        getReminderUseCase.setId(0);
        getReminderUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(RemindersDoubles.REMINDER);
    }

    @Test
    public void testGetReminderByIdNotFound() throws Exception {
        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        getReminderUseCase.setId(1);
        getReminderUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertNoValues();
    }

    @Test
    public void testGetReminderByIdErrorIdNotSet() throws Exception {
        TestSubscriber<Reminder> testSubscriber = new TestSubscriber<>();
        getReminderUseCase.execute(testSubscriber);

        testSubscriber.assertError(NoSuchElementException.class);
    }
}