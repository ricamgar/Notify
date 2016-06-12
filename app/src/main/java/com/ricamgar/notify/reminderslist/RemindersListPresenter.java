package com.ricamgar.notify.reminderslist;

import com.ricamgar.notify.base.view.BasePresenter;
import com.ricamgar.notify.base.view.BaseView;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.usecase.DeleteReminder;
import com.ricamgar.notify.domain.reminder.usecase.GetReminders;
import com.ricamgar.notify.domain.reminder.usecase.MarkReminderAsDone;
import com.ricamgar.notify.domain.reminder.usecase.MarkReminderAsTodo;

import java.util.List;

import javax.inject.Inject;

import rx.Subscriber;

public class RemindersListPresenter extends BasePresenter {

    private final GetReminders getRemindersUseCase;
    private final MarkReminderAsDone markReminderAsDoneUseCase;
    private final MarkReminderAsTodo markReminderAsTodo;
    private final DeleteReminder deleteReminder;
    private View view;

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
    protected void attachToView(BaseView view) {
        this.view = (View) view;

    }

    public void initialize(int position) {
        getRemindersUseCase.history(position == 1).execute(new GetRemindersSubscriber());
    }

    @Override
    protected void detachFromView() {
        super.detachFromView();
        getRemindersUseCase.unsubscribe();
    }

    public void reminderMarked(Reminder reminder, boolean done) {
        if (done) {
            markReminderAsDoneUseCase.setReminder(reminder);
            markReminderAsDoneUseCase.execute();
        } else {
            markReminderAsTodo.setReminder(reminder);
            markReminderAsTodo.execute();
        }
    }

    public void reminderDeleted(Reminder reminder) {
        deleteReminder.setReminder(reminder);
        deleteReminder.execute();
    }

    private final class GetRemindersSubscriber extends Subscriber<List<Reminder>> {

        @Override
        public void onCompleted() {
            view.hideProgress();
        }

        @Override
        public void onError(Throwable e) {
            view.showToast(e.getMessage());
        }

        @Override
        public void onNext(List<Reminder> reminders) {
            view.setReminders(reminders);
            view.hideProgress();
        }
    }

    public interface View extends BaseView {
        void setReminders(List<Reminder> reminders);
    }

}
