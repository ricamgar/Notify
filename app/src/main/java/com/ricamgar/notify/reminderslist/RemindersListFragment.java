package com.ricamgar.notify.reminderslist;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ricamgar.notify.R;
import com.ricamgar.notify.base.mvp.BaseFragment;
import com.ricamgar.notify.domain.reminder.model.Reminder;
import com.ricamgar.notify.main.NotifyApp;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class RemindersListFragment
        extends BaseFragment<List<Reminder>, RemindersListPresenter>
        implements RemindersListPresenter.View, RemindersListAdapter.ReminderListener {

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
        NotifyApp.getApplicationComponent().inject(this);
        super.onCreate(savedInstanceState);
        position = getArguments().getInt(FRAGMENT_POSITION);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reminders_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        adapter = new RemindersListAdapter(this, position);
        remindersList = view.findViewById(R.id.remindersList);
        remindersList.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        remindersList.setAdapter(adapter);
    }

    @Override
    protected List<Reminder> createViewModel() {
        return new ArrayList<>(0);
    }

    @Override
    protected RemindersListPresenter initializePresenter() {
        return presenter.setPosition(position);
    }

    @Override
    public void setReminders(List<Reminder> reminders) {
        adapter.call(reminders);
    }

    @Override
    public void showError(Throwable throwable) {
        Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
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
