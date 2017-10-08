package com.ricamgar.notify.addreminder

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.Toast
import com.jakewharton.rxbinding.widget.RxTextView
import com.ricamgar.notify.R
import com.ricamgar.notify.base.mvp.BaseActivity
import com.ricamgar.notify.domain.reminder.model.Reminder
import com.ricamgar.notify.main.NotifyApp
import javax.inject.Inject

class AddReminderActivity : BaseActivity<Reminder, AddReminderPresenter>(), AddReminderPresenter.View {

  @Inject
  lateinit var presenter: AddReminderPresenter

  public override fun onCreate(savedInstanceState: Bundle?) {
    NotifyApp.getApplicationComponent().inject(this)
    super.onCreate(savedInstanceState)

    setContentView(R.layout.activity_add_reminder)

    val descriptionEt = findViewById<EditText>(R.id.add_description)
    descriptionEt.setOnEditorActionListener { v, actionId, event ->
      if (actionId == EditorInfo.IME_ACTION_DONE) {
        presenter.insertReminder()
      }
      false
    }
    RxTextView.textChanges(descriptionEt).subscribe { presenter.setDescription(it) }
  }

  override fun createViewModel(): Reminder = Reminder.NULL

  override fun initializePresenter(): AddReminderPresenter? = presenter

  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.add_reminder, menu)
    return true
  }

  override fun onOptionsItemSelected(item: MenuItem?): Boolean {
    when (item!!.itemId) {
      R.id.save -> presenter.insertReminder()
    }
    return super.onOptionsItemSelected(item)
  }

  override fun showError(error: String) {
    Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
  }

  override fun showReminderCreatedSuccess() {
    Toast.makeText(this, "Reminder created", Toast.LENGTH_SHORT).show()
  }

  override fun closeDialog() {
    finish()
  }

  companion object {
    fun getIntent(context: Context): Intent = Intent(context, AddReminderActivity::class.java)
  }
}
