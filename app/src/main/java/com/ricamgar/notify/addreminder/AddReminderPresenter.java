package com.ricamgar.notify.addreminder;


import com.ricamgar.notify.base.view.BasePresenter;
import com.ricamgar.notify.base.view.BaseView;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.domain.reminder.usecase.AddOrUpdateReminder;

import javax.inject.Inject;

import rx.Subscriber;

public class AddReminderPresenter extends BasePresenter {

    private final AddOrUpdateReminder addReminderUseCase;
    private View view;
    private final Reminder.Builder reminderBuilder = new Reminder.Builder();

    @Inject
    public AddReminderPresenter(AddOrUpdateReminder addReminderUseCase) {
        this.addReminderUseCase = addReminderUseCase;
    }

    @Override
    protected void attachToView(BaseView view) {
        this.view = (View) view;
    }

    @Override
    protected void detachFromView() {
        super.detachFromView();
        addReminderUseCase.unsubscribe();
        view = null;
    }

    public void insertReminder() {
        Reminder reminder = reminderBuilder.build();
        if (reminder.description.isEmpty()) {
            view.showToast("Invalid reminder");
            return;
        }

        addReminderUseCase.setReminder(reminder).execute(new AddReminderSubscriber());
    }

    public void setDescription(CharSequence description) {
        reminderBuilder.description(description.toString());
    }

    private final class AddReminderSubscriber extends Subscriber<Reminder> {

        @Override
        public void onCompleted() {

        }

        @Override
        public void onError(Throwable e) {
            view.showToast(e.getMessage());
        }

        @Override
        public void onNext(Reminder reminder) {
            view.showToast("Reminder added");
            view.closeDialog();
        }
    }

    public interface View extends BaseView {
        void closeDialog();
    }
}
