package com.example.to_do_app.ui.insertevent

import android.annotation.SuppressLint
import android.app.TimePickerDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.to_do_app.R
import com.example.to_do_app.database.EventModel
import com.example.to_do_app.notification.NotificationHelper
import com.example.to_do_app.ui.edit.EditActivity
import kotlinx.android.synthetic.main.activity_insert_event.*
import java.util.*

class InsertEventActivity : AppCompatActivity() {

    lateinit var dateValue: String
    private lateinit var viewModel: InsertViewModel
    lateinit var buttonStartTime: Button
    lateinit var buttonEndTime: Button
    lateinit var submitButton: Button

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_event)

        viewModel =
                ViewModelProvider(this).get(InsertViewModel::class.java)

        buttonStartTime = findViewById(R.id.btnStartTime)
        buttonEndTime = findViewById(R.id.btnEndTime)
        submitButton = findViewById(R.id.submitBtn)

        // receive intent
        dateValue = intent.getStringExtra("Date").toString()
        dateText.text = getString(R.string.date_text_view) + " " + dateValue

        submitButton.setOnClickListener { captureData() }
    }

    private fun captureData() {
        viewModel.date = dateText.text.toString()
        viewModel.title = titleText.text.toString()
        viewModel.startTime = btnStartTime.text.toString()
        viewModel.endTime = btnEndTime.text.toString()
        viewModel.desc = descriptionText.text.toString()

        if (viewModel.formValidate()) {
            val item = EventModel(0,
                    viewModel.title,
                    dateValue,
                    viewModel.startTime,
                    viewModel.endTime,
                    viewModel.desc
            )

            viewModel.insertEvent(item)

            moveActivity()
            showNotification()
        } else {
            Toast.makeText(
                    this,
                    "Please fill in form fields",
                    Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun moveActivity() {
        val intent = Intent(this, EditActivity::class.java).apply {
            putExtra("Date", dateValue)
            flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        startActivity(intent)
    }

    private fun showNotification() {
        val notificationHelper = NotificationHelper()
        notificationHelper.sendNotification(this,
                viewModel.title,
                "Your event will begin at ${viewModel.startTime} and end at ${viewModel.endTime}.")
    }

    /**
     * time picker functions
     * (Eyehunt, 2018)
     * https://tutorial.eyehunts.com/android/android-time-picker-dialog-example-kotlin/
     */
    @RequiresApi(Build.VERSION_CODES.N)
    fun clickTimePickerStart(view: View) {

        val c = Calendar.getInstance()

        val hour = c.get(Calendar.HOUR)
        var minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, { _, hour, minute ->
            var hourString = hour.toString()
            var minuteString = minute.toString()

            if (hour < 10) {
                hourString = "0$hourString"
            }

            if (minute < 10) {
                minuteString = "0$minuteString"
            }

            val date = "$hourString:$minuteString"

            Toast.makeText(this, date, Toast.LENGTH_LONG).show()
            btnStartTime.text = date
        }, hour, minute, false)

        tpd.show()
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun clickTimePickerEnd(view: View) {
        val c = Calendar.getInstance()

        val hour = c.get(Calendar.HOUR)
        var minute = c.get(Calendar.MINUTE)

        val tpd = TimePickerDialog(this, { _, hour, minute ->
            var hourString = hour.toString()
            var minuteString = minute.toString()

            if (hour < 10) {
                hourString = "0$hourString"
            }

            if (minute < 10) {
                minuteString = "0$minuteString"
            }

            val date = "$hourString:$minuteString"

            Toast.makeText(this, date, Toast.LENGTH_LONG).show()
            btnEndTime.text = date
        }, hour, minute, false)

        tpd.show()
    }
}