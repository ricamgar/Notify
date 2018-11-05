package com.ricamgar.notify.main

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.support.v4.app.NotificationManagerCompat
import android.support.v4.content.ContextCompat
import com.ricamgar.notify.R
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Scheduler

internal class NotificationsController(
  private val context: Context,
  remindersRepository: RemindersRepository,
  ioThread: Scheduler) {

  private val CHANNEL_ID = "Notify"
  private val notificationManager: NotificationManagerCompat = NotificationManagerCompat.from(context)

  init {
    createNotificationChannel()
    val subscribe = remindersRepository.todoReminders
      .subscribeOn(ioThread)
      .subscribe { this.showNotification(it) }
  }

  private fun createNotificationChannel() {
    // Create the NotificationChannel, but only on API 26+ because
    // the NotificationChannel class is new and not in the support library
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      val importance = NotificationManager.IMPORTANCE_DEFAULT
      val channel = NotificationChannel(CHANNEL_ID, "Notify", importance).apply {
        description = "Notify notifications"
      }
      // Register the channel with the system
      val notificationManager: NotificationManager =
        context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
      notificationManager.createNotificationChannel(channel)
    }
  }

  private fun showNotification(reminders: List<Reminder>) {

    if (reminders.isEmpty()) {
      notificationManager.cancel(0)
      return
    }

    val builder = NotificationCompat.Builder(context, CHANNEL_ID)
      .setTicker("Notify")
      .setContentTitle("Notify")
      .setContentText(reminders[0].description)
      .setSmallIcon(R.drawable.ic_fish)
      .setColor(ContextCompat.getColor(context, R.color.primary))
      .setOngoing(true)
      .setAutoCancel(false)
      .setContentIntent(createContentIntent())
      .setDefaults(Notification.DEFAULT_ALL)


    if (reminders.size > 1) {
      val inboxStyle = NotificationCompat.InboxStyle()
      val size = reminders.size
      for (i in 0 until size) {
        inboxStyle.addLine(reminders[i].description)
      }
      inboxStyle
        .setBigContentTitle("Notify")
        .setSummaryText(size.toString() + " Reminders")
      builder
        .setStyle(inboxStyle)
        .setContentText(size.toString() + " Reminders")
    }

    val notification = builder.build()
    notificationManager.notify(0, notification)

  }

  private fun createContentIntent(): PendingIntent {
    val intent = Intent(context, MainActivity::class.java)
    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
    return PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)
  }
}
