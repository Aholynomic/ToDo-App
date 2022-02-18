package com.example.to_do_app.ui.schedule

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.to_do_app.R
import com.example.to_do_app.ui.edit.EditActivity

class ScheduleFragment : Fragment() {

    // promises
    private lateinit var viewModel: ScheduleViewModel
    private lateinit var calendar: CalendarView

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // create an instance of view model
        viewModel =
                ViewModelProvider(this).get(ScheduleViewModel::class.java)

        // inflate the current fragment
        val root = inflater.inflate(R.layout.fragment_schedule, container, false)

        calendar = root.findViewById(R.id.calendar_view)!!

        // on click listener for calendar view
        calendar.setOnDateChangeListener { _, year: Int, month: Int, day: Int ->
            changeValues(day, month, year)
            moveActivity(viewModel.dateValue)
        }

        return root
    }

    /**
     * get the values from the calendar and format them
     */
    private fun changeValues(day: Int, month: Int, year: Int) {
        viewModel.changeValues(day, month, year)
    }

    /**
     * move activity and pass the Date value to the next activity
     */
    private fun moveActivity(dateValue: String) {
        Toast.makeText(
                activity,
                dateValue,
                Toast.LENGTH_SHORT
        ).show()

        val intent = Intent(activity, EditActivity::class.java).apply {
            putExtra("Date", dateValue)
        }

        startActivity(intent)
    }
}