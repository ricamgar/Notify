package com.ricamgar.notify.main;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class BootCompletedReceiver extends BroadcastReceiver {

    private static final String TAG = BootCompletedReceiver.class.getSimpleName();

    public BootCompletedReceiver() {
//        App.getApplicationComponent().inject(this);
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        switch (action) {
//            case RemindersNotifications.NOTIFICATION_DELETED_ACTION:
//                Reminder reminderExtra = (Reminder) intent.getSerializableExtra(RemindersNotifications.EXTRA_REMINDER);
//                Reminder.Builder builder = new Reminder.Builder(reminderExtra);
//                builder.enabled(false);
//                addOrUpdateReminderUseCase.addOrUpdate(builder.build()).execute();
//                break;

            case Intent.ACTION_BOOT_COMPLETED:
                Log.e(TAG, "BootCompletedReceived!");
//                new NotificationsController(context);
                break;
        }
    }
}
