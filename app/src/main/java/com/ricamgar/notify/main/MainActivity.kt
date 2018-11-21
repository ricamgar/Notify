package com.ricamgar.notify.main

import android.content.Context
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.tabs.TabLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import androidx.appcompat.app.AppCompatActivity

import com.ricamgar.notify.R
import com.ricamgar.notify.addreminder.AddReminderActivity
import com.ricamgar.notify.reminderslist.RemindersListFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val fab = findViewById<FloatingActionButton>(R.id.fab)
    fab.setOnClickListener { openAddReminderActivity() }

    val viewPager = findViewById<ViewPager>(R.id.viewpager)
    viewPager.adapter = MainAdapter(this, supportFragmentManager)
    viewPager.addOnPageChangeListener(object : ViewPager.SimpleOnPageChangeListener() {
      override fun onPageSelected(position: Int) {
        if (position == 0) {
          fab.show()
        } else {
          fab.hide()
        }
      }
    })

    val tabLayout = findViewById<TabLayout>(R.id.tabs)
    tabLayout.setupWithViewPager(viewPager)
  }

  private fun openAddReminderActivity() {
    startActivity(AddReminderActivity.getIntent(this))
  }

  private class MainAdapter internal constructor(private val context: Context, fm: FragmentManager) : FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment = RemindersListFragment.newInstance(position)

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence {
      return if (position == 0)
        context.getString(R.string.tab_reminders)
      else
        context.getString(R.string.tab_history)
    }
  }

}
