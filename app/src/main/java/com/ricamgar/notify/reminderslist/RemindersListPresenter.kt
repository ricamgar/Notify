package com.ricamgar.notify.reminderslist

import com.ricamgar.notify.base.mvp.AbstractBasePresenter
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.domain.reminder.usecase.DeleteReminder
import com.ricamgar.notify.domain.reminder.usecase.GetReminders
import com.ricamgar.notify.domain.reminder.usecase.MarkReminderAsDone
import com.ricamgar.notify.domain.reminder.usecase.MarkReminderAsTodo
import io.reactivex.Scheduler

import javax.inject.Inject
import javax.inject.Named

internal class RemindersListPresenter
@Inject constructor(
  private val getRemindersUseCase: GetReminders,
  private val markReminderAsDoneUseCase: MarkReminderAsDone,
  private val markReminderAsTodo: MarkReminderAsTodo,
  private val deleteReminder: DeleteReminder,
  @Named("io") ioThread: Scheduler,
  @Named("main") mainThread: Scheduler
) : AbstractBasePresenter<MutableList<Reminder>>(ioThread, mainThread) {

  private var position: Int = 0

  override fun attachToView(view: BaseView, viewModel: MutableList<Reminder>?) {
    super.attachToView(view, viewModel)
    if (viewModel != null && viewModel.isEmpty()) {
      addDisposable(getRemindersUseCase.execute(position == 1)
        .compose(flowableSchedulers())
        .subscribe(
          { reminders ->
            viewModel.clear()
            viewModel.addAll(reminders)
            (view as View).setReminders(reminders)
          },
          { (view as View).showError(it) }
        ))
    } else {
      (view as View).setReminders(viewModel)
    }
  }

  fun reminderMarked(reminder: Reminder, done: Boolean) {
    if (done) {
      markReminderAsDoneUseCase.execute(reminder)
        .subscribe()
    } else {
      markReminderAsTodo.execute(reminder)
        .subscribe()
    }
  }

  fun reminderDeleted(reminder: Reminder) {
    deleteReminder.execute(reminder.id)
      .subscribe()
  }

  fun setPosition(position: Int): RemindersListPresenter {
    this.position = position
    return this
  }

  interface View : AbstractBasePresenter.BaseView {
    fun setReminders(reminders: List<Reminder>?)

    fun showError(throwable: Throwable)
  }
}
