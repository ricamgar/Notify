package com.ricamgar.notify.addreminder;

import com.ricamgar.notify.base.mvp.AbstractBasePresenter;
import com.ricamgar.notify.base.mvp.BaseView;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.usecase.AddOrUpdateReminder;

import javax.inject.Inject;

public class AddReminderPresenter extends AbstractBasePresenter<AddReminderPresenter.View, Reminder> {

    private final AddOrUpdateReminder addReminderUseCase;
    private final Reminder.Builder reminderBuilder = new Reminder.Builder();

    @Inject
    public AddReminderPresenter(AddOrUpdateReminder addReminderUseCase) {
        this.addReminderUseCase = addReminderUseCase;
    }

    public void insertReminder() {
        Reminder reminder = reminderBuilder.build();
        if (reminder.description.isEmpty()) {
            view.showError("Invalid reminder");
            return;
        }

        addSubscription(addReminderUseCase.setReminder(reminder)
                .execute()
                .subscribe(
                        insertedReminder -> {
                            view.showReminderCreatedSuccess();
                            view.closeDialog();
                        },
                        throwable -> view.showError(throwable.getMessage())
                ));
    }

    public void setDescription(CharSequence description) {
        reminderBuilder.description(description.toString());
    }

    public interface View extends BaseView {

        void closeDialog();

        void showError(String error);

        void showReminderCreatedSuccess();
    }
}
