package com.ricamgar.notify.data.reminder

import com.ricamgar.notify.data.reminder.mapper.ReminderToEntity
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.repository.RemindersRepository
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

class ReminderSqliteRepository(private val database: ReminderDao) : RemindersRepository {

  override val todoReminders: Flowable<List<Reminder>>
    get() = database.todoReminders()

  override val historyReminders: Flowable<List<Reminder>>
    get() = database.historyReminders()

  override fun getById(id: Long): Single<Reminder> {
    return Single.fromCallable {
      database.reminder(id)
    }
  }

  override fun addOrUpdate(reminder: Reminder): Single<Reminder> {
    return Single.fromCallable {
      val rowId = database.add(ReminderToEntity().map(reminder))
      database.reminder(rowId)
    }
  }

  override fun delete(id: Long): Completable {
    return Completable.fromAction {
      database.delete(id)
    }
  }
}
