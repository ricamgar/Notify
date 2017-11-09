package com.ricamgar.notify.data.reminder

import com.ricamgar.notify.data.database.AppDatabase
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single
import javax.inject.Inject

internal class ReminderSqliteRepository
@Inject constructor(database: AppDatabase) : RemindersRepository {

  private val remindersDao = database.reminderModel()
  private val mapper = ReminderToEntity()

  override val todoReminders: Flowable<List<Reminder>>
    get() = remindersDao.todoReminders()

  override val historyReminders: Flowable<List<Reminder>>
    get() = remindersDao.historyReminders()

  override fun getById(id: Long): Single<Reminder> {
    return Single.fromCallable {
      remindersDao.reminder(id)
    }
  }

  override fun addOrUpdate(reminder: Reminder): Single<Reminder> {
    return Single.fromCallable {
      val rowId = remindersDao.add(mapper.map(reminder))
      remindersDao.reminder(rowId)
    }
  }

  override fun delete(id: Long): Completable {
    return Completable.fromAction {
      remindersDao.delete(id)
    }
  }
}
