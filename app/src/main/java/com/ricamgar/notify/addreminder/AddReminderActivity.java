package com.ricamgar.notify.addreminder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatCheckBox;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.jakewharton.rxbinding.widget.RxCompoundButton;
import com.jakewharton.rxbinding.widget.RxTextView;
import com.ricamgar.notify.R;
import com.ricamgar.notify.base.view.BaseActivity;
import com.ricamgar.notify.main.NotifyApp;

import javax.inject.Inject;

public class AddReminderActivity extends BaseActivity implements AddReminderPresenter.View {

    @Inject
    AddReminderPresenter presenter;

    public static Intent getIntent(Context context) {
        return new Intent(context, AddReminderActivity.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotifyApp.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_add_reminder);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        EditText descriptionEt = (EditText) findViewById(R.id.add_description);
        RxTextView.textChanges(descriptionEt).subscribe(presenter::setDescription);

        findViewById(R.id.add_btn).setOnClickListener(view -> presenter.insertReminder());

        presenter.attachToView(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void closeDialog() {
        finish();
    }
}
