package com.ricamgar.notify.addreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxTextView;
import com.ricamgar.notify.R;
import com.ricamgar.notify.base.mvp.BaseActivity;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.main.NotifyApp;

import javax.inject.Inject;

public class AddReminderActivity
        extends BaseActivity<Reminder, AddReminderPresenter>
        implements AddReminderPresenter.View {

    @Inject
    AddReminderPresenter presenter;

    public static Intent getIntent(Context context) {
        return new Intent(context, AddReminderActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        NotifyApp.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_reminder);

        EditText descriptionEt = (EditText) findViewById(R.id.add_description);
        descriptionEt.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                presenter.insertReminder();
                return true;
            }
            return false;
        });
        RxTextView.textChanges(descriptionEt).subscribe(presenter::setDescription);

        findViewById(R.id.add_btn).setOnClickListener(view -> presenter.insertReminder());
    }

    @Override
    protected Reminder createViewModel() {
        return Reminder.NULL;
    }

    @Override
    protected AddReminderPresenter initializePresenter() {
        return presenter;
    }

    @Override
    public void showError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showReminderCreatedSuccess() {
        Toast.makeText(this, "Reminder created", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialog() {
        finish();
    }
}
