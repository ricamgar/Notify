package com.ricamgar.notify.reminderslist;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jakewharton.rxbinding.view.RxView;
import com.ricamgar.notify.R;
import com.ricamgar.notify.addreminder.AddReminderActivity;
import com.ricamgar.notify.base.view.BaseActivity;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.main.NotifyApp;

import java.util.List;

import javax.inject.Inject;

public class RemindersListFragment extends Fragment implements RemindersListPresenter.View, RemindersListAdapter.ReminderListener {

    private static final String FRAGMENT_POSITION = "FRAGMENT_POSITION";

    @Inject
    protected RemindersListPresenter presenter;

    private RemindersListAdapter adapter;
    private RecyclerView remindersList;
    private int position;

    public static RemindersListFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt(FRAGMENT_POSITION, position);
        RemindersListFragment fragment = new RemindersListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NotifyApp.getApplicationComponent().inject(this);
        position = getArguments().getInt(FRAGMENT_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        adapter = new RemindersListAdapter(getContext(), this, position);
        remindersList = (RecyclerView) view.findViewById(R.id.remindersList);
        remindersList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        remindersList.setAdapter(adapter);

        presenter.attachToView(this);
        presenter.initialize(position);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachFromView();
    }

    @Override
    public void setReminders(List<Reminder> reminders) {
        adapter.call(reminders);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showToast(String message) {

    }

    @Override
    public void onReminderClicked(Reminder reminder) {

    }

    @Override
    public void onReminderChecked(Reminder reminder, boolean checked) {
        presenter.reminderMarked(reminder, checked);
    }

    @Override
    public void onReminderDeleted(Reminder reminder) {
        presenter.reminderDeleted(reminder);
    }
}
