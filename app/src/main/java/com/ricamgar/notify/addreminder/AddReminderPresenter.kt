package com.ricamgar.notify.addreminder

import com.ricamgar.notify.base.mvp.AbstractBasePresenter
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.usecase.AddOrUpdateReminder
import io.reactivex.Scheduler

import javax.inject.Inject
import javax.inject.Named

class AddReminderPresenter
@Inject constructor(
  private val addReminderUseCase: AddOrUpdateReminder,
  @Named("io") ioThread: Scheduler,
  @Named("main") mainThread: Scheduler
) : AbstractBasePresenter<Reminder>(ioThread, mainThread) {

  private lateinit var description: String

  fun insertReminder() {
    if (description.isEmpty()) {
      (view as View).showError("Invalid reminder")
      return
    }

    val reminder = Reminder(0, description, false, 0)
    addDisposable(addReminderUseCase.execute(reminder)
      .compose(singleSchedulers())
      .subscribe({
        (view as View).showReminderCreatedSuccess()
        (view as View).closeDialog()
      }, { throwable -> (view as View).showError(throwable.message!!) }
      ))
  }

  fun setDescription(description: CharSequence) {
    this.description = description.toString()
  }

  interface View : AbstractBasePresenter.BaseView {

    fun closeDialog()

    fun showError(error: String)

    fun showReminderCreatedSuccess()
  }
}
