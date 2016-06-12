package com.ricamgar.notify.main;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.content.ContextCompat;

import com.ricamgar.notify.R;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository;

import java.util.List;

import rx.schedulers.Schedulers;

public class NotificationsController {

    private final Context context;
    private final NotificationManagerCompat notificationManager;

    NotificationsController(Context context, RemindersRepository remindersRepository) {
        this.context = context;
        notificationManager = NotificationManagerCompat.from(context);
        remindersRepository.getTodoReminders()
                .subscribeOn(Schedulers.io())
                .subscribe(this::showNotification);
    }

    public void showNotification(List<Reminder> reminders) {

        if (reminders.isEmpty()) {
            notificationManager.cancel(0);
            return;
        }

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setTicker("Notify")
                .setContentTitle("Notify")
                .setContentText(reminders.get(0).description)
                .setSmallIcon(R.drawable.ic_fish)
                .setColor(ContextCompat.getColor(context, R.color.primary))
                .setOngoing(true)
                .setAutoCancel(false)
                .setContentIntent(createContentIntent())
                .setDefaults(Notification.DEFAULT_ALL);


        if (reminders.size() > 1) {
            NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
            int size = reminders.size();
            for (int i = 0; i < size; i++) {
                inboxStyle.addLine(reminders.get(i).description);
            }
            inboxStyle
                    .setBigContentTitle("Notify")
                    .setSummaryText(size + " Reminders");
            builder
                    .setStyle(inboxStyle)
                    .setContentText(size + " Reminders");
        }
        //              .setContentIntent(contentIntent)
        //              .setDeleteIntent(deleteIntent)


        Notification notification = builder.build();
        notificationManager.notify(0, notification);

    }

    private PendingIntent createContentIntent() {
        Intent intent = new Intent(context, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
    }
}
