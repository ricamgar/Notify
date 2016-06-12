package com.ricamgar.notify.data.reminder.mapper;

import android.database.MatrixCursor;

import com.ricamgar.notify.data.reminder.ReminderContract;
import com.ricamgar.notify.domain.reminder.model.Reminder;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CursorToReminderTest {

    private static String[] columns = new String[]{ReminderContract._ID, ReminderContract.DESCRIPTION};
    private static final MatrixCursor REMINDER_CURSOR = new MatrixCursor(columns);
    private static final Reminder REMINDER = new Reminder(0, "description", true, false);

    CursorToReminder cursorToReminderMapper;

    @Before
    public void setUp() throws Exception {
        REMINDER_CURSOR.addRow(new Object[]{REMINDER.id, REMINDER.description});

        cursorToReminderMapper = new CursorToReminder();
    }

    @Test
    public void testMapCursorToReminder() throws Exception {
//        Reminder result = cursorToReminderMapper.map(REMINDER_CURSOR);
//
//        assertEquals(result.id, REMINDER.id);
//        assertEquals(result.description, REMINDER.description);
    }
}