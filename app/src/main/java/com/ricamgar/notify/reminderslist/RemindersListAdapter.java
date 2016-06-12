package com.ricamgar.notify.reminderslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.jakewharton.rxbinding.view.RxView;
import com.ricamgar.notify.R;
import com.ricamgar.notify.domain.reminder.model.Reminder;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.functions.Action1;

public class RemindersListAdapter extends RecyclerView.Adapter<RemindersListAdapter.ReminderViewHolder> implements
        Action1<List<Reminder>> {

    private final ReminderListener listener;
    private final int mode;
    private final Context context;
    private List<Reminder> items = Collections.emptyList();

    public RemindersListAdapter(Context context, ReminderListener listener, int mode) {
        this.context = context;
        this.listener = listener;
        this.mode = mode;
    }

    @Override
    public ReminderViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.reminder_item, viewGroup, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ReminderViewHolder reminderViewHolder, int position) {
        Reminder reminder = items.get(position);
        reminderViewHolder.description.setText(reminder.description);
        reminderViewHolder.enabled.setText(reminder.done ? "SHOW NOTIFICATION" : "MARK AS DONE");
        RxView.clicks(reminderViewHolder.enabled)
                .doOnNext(o1 -> {
                    items.remove(position);
                    notifyItemRemoved(position);
                })
                .delay(500, TimeUnit.MILLISECONDS)
                .subscribe(o -> listener.onReminderChecked(reminder, !reminder.done));

        reminderViewHolder.itemView.setOnClickListener(view -> {
            listener.onReminderClicked(reminder);
        });
        if (mode == 0) {
            reminderViewHolder.delete.setVisibility(View.GONE);
        } else {
            RxView.clicks(reminderViewHolder.delete)
                    .doOnNext(o1 -> {
                        items.remove(position);
                        notifyItemRemoved(position);
                    })
                    .delay(500, TimeUnit.MILLISECONDS)
                    .subscribe(o -> listener.onReminderDeleted(reminder));

            reminderViewHolder.delete.setVisibility(View.VISIBLE);
        }

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    @Override
    public void call(List<Reminder> newItems) {
        this.items = newItems;
        notifyDataSetChanged();
    }

    final static class ReminderViewHolder extends RecyclerView.ViewHolder {

        public final TextView description;
        public final TextView enabled;
        public final View delete;

        public ReminderViewHolder(View itemView) {
            super(itemView);
            description = (TextView) itemView.findViewById(R.id.description);
            enabled = (TextView) itemView.findViewById(R.id.enable_btn);
            delete = itemView.findViewById(R.id.delete);
        }
    }

    public interface ReminderListener {
        void onReminderClicked(Reminder reminder);

        void onReminderChecked(Reminder reminder, boolean checked);

        void onReminderDeleted(Reminder reminder);
    }
}
