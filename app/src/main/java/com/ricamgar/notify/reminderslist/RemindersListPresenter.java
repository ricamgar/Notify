package com.ricamgar.notify.reminderslist;

import android.support.annotation.Nullable;

import com.ricamgar.notify.base.mvp.AbstractBasePresenter;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.usecase.DeleteReminder;
import com.ricamgar.notify.domain.reminder.usecase.GetReminders;
import com.ricamgar.notify.domain.reminder.usecase.MarkReminderAsDone;
import com.ricamgar.notify.domain.reminder.usecase.MarkReminderAsTodo;

import java.util.List;

import javax.inject.Inject;

public class RemindersListPresenter
        extends AbstractBasePresenter<List<Reminder>> {

    private final GetReminders getRemindersUseCase;
    private final MarkReminderAsDone markReminderAsDoneUseCase;
    private final MarkReminderAsTodo markReminderAsTodo;
    private final DeleteReminder deleteReminder;
    private int position;

    @Inject
    public RemindersListPresenter(GetReminders getRemindersUseCase,
                                  MarkReminderAsDone markReminderAsDoneUseCase,
                                  MarkReminderAsTodo markReminderAsTodo,
                                  DeleteReminder deleteReminder) {
        this.getRemindersUseCase = getRemindersUseCase;
        this.markReminderAsDoneUseCase = markReminderAsDoneUseCase;
        this.markReminderAsTodo = markReminderAsTodo;
        this.deleteReminder = deleteReminder;
    }

    @Override
    public void attachToView(BaseView view, @Nullable List<Reminder> viewModel) {
        super.attachToView(view, viewModel);
        if (viewModel.isEmpty()) {
            addSubscription(getRemindersUseCase.history(position == 1)
                    .execute()
                    .subscribe(
                            reminders -> {
                                viewModel.clear();
                                viewModel.addAll(reminders);
                                ((View) view).setReminders(reminders);
                            },
                            ((View) view)::showError
                    ));
        } else {
            ((View) view).setReminders(viewModel);
        }
    }

    public void reminderMarked(Reminder reminder, boolean done) {
        if (done) {
            markReminderAsDoneUseCase.setReminder(reminder);
            markReminderAsDoneUseCase
                    .execute()
                    .subscribe();
        } else {
            markReminderAsTodo.setReminder(reminder);
            markReminderAsTodo
                    .execute()
                    .subscribe();
        }
    }

    public void reminderDeleted(Reminder reminder) {
        deleteReminder.setReminder(reminder);
        deleteReminder
                .execute()
                .subscribe();
    }

    public RemindersListPresenter setPosition(int position) {
        this.position = position;
        return this;
    }

    public interface View extends BaseView {
        void setReminders(List<Reminder> reminders);

        void showError(Throwable throwable);
    }
}
