package com.ricamgar.notify.domain.reminder.repository

import com.ricamgar.notify.domain.reminder.model.Reminder
import io.reactivex.Completable
import io.reactivex.Flowable

import io.reactivex.Observable
import io.reactivex.Single

interface RemindersRepository {

  val todoReminders: Flowable<List<Reminder>>

  val historyReminders: Flowable<List<Reminder>>

  fun getById(id: Long): Single<Reminder>

  fun addOrUpdate(reminder: Reminder): Single<Reminder>

  fun delete(id: Long): Completable
}
