package com.ricamgar.notify.domain.reminder.usecase;

import com.ricamgar.notify.domain.base.usecase.UseCaseTest;
import com.ricamgar.notify.domain.reminder.RemindersDoubles;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.Collections;
import java.util.List;

import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Mockito.when;

public class GetRemindersTest extends UseCaseTest{

    @Mock
    RemindersRepository mockRepository;
    GetReminders getRemindersUseCase;

    @Override
    @Before
    public void setUp() throws Exception {
        super.setUp();
        getRemindersUseCase = new GetReminders(mockRepository, executionThread, postExecutionThread);
    }

    @Test
    public void testGetAllReminders() throws Exception {
        when(mockRepository.getAll()).thenReturn(Observable.just(RemindersDoubles.REMINDERS_LIST));

        TestSubscriber<List<Reminder>> testSubscriber = new TestSubscriber<>();
        getRemindersUseCase.execute(testSubscriber);

        testSubscriber.assertNoErrors();
        testSubscriber.assertValue(RemindersDoubles.REMINDERS_LIST);
    }
}