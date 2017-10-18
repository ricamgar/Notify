package com.ricamgar.notify.reminderslist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import com.jakewharton.rxbinding.view.RxView
import com.ricamgar.notify.R
import com.ricamgar.notify.domain.reminder.model.Reminder

import java.util.concurrent.TimeUnit

import rx.functions.Action1

class RemindersListAdapter(
  private val listener: ReminderListener,
  private val mode: Int
) : RecyclerView.Adapter<RemindersListAdapter.ReminderViewHolder>(), Action1<MutableList<Reminder>> {

  private var items: MutableList<Reminder> = mutableListOf()

  override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ReminderViewHolder {
    val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.reminder_item, viewGroup, false)
    return ReminderViewHolder(view)
  }

  override fun onBindViewHolder(reminderViewHolder: ReminderViewHolder, position: Int) {
    val reminder = items[position]
    reminderViewHolder.description.text = reminder.description
    reminderViewHolder.enabled.text = if (reminder.done) "SHOW NOTIFICATION" else "MARK AS DONE"
    RxView.clicks(reminderViewHolder.enabled)
      .doOnNext {
        items.removeAt(position)
        notifyItemRemoved(position)
      }
      .delay(500, TimeUnit.MILLISECONDS)
      .subscribe { listener.onReminderChecked(reminder, !reminder.done) }

    reminderViewHolder.itemView.setOnClickListener { listener.onReminderClicked(reminder) }
    if (mode == 0) {
      reminderViewHolder.delete.visibility = View.GONE
    } else {
      RxView.clicks(reminderViewHolder.delete)
        .doOnNext {
          items.removeAt(position)
          notifyItemRemoved(position)
        }
        .delay(500, TimeUnit.MILLISECONDS)
        .subscribe { listener.onReminderDeleted(reminder) }

      reminderViewHolder.delete.visibility = View.VISIBLE
    }

  }

  override fun getItemCount(): Int = items.size

  override fun call(newItems: MutableList<Reminder>) {
    this.items = newItems
    notifyDataSetChanged()
  }

  class ReminderViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val description: TextView = itemView.findViewById<View>(R.id.description) as TextView
    val enabled: TextView = itemView.findViewById<View>(R.id.enable_btn) as TextView
    val delete: View = itemView.findViewById(R.id.delete)
  }

  interface ReminderListener {
    fun onReminderClicked(reminder: Reminder)

    fun onReminderChecked(reminder: Reminder, checked: Boolean)

    fun onReminderDeleted(reminder: Reminder)
  }
}
