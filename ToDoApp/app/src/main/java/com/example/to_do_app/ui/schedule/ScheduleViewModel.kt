package com.example.to_do_app.ui.schedule

import androidx.lifecycle.ViewModel

class ScheduleViewModel : ViewModel() {

    private var dayString = ""
    private var monthString = ""
    private var yearString = ""

    lateinit var dateValue: String

    /**
     * convert the calendar values to string
     */
    fun changeValues(day: Int, month: Int, year: Int) {
        dayString = day.toString()
        val modifiedMonth = month + 1
        monthString = modifiedMonth.toString()
        yearString = year.toString()

        dayString = addFormat(day, dayString)
        monthString = addFormat(month, monthString)


        // concatenate the strings to form the date
        dateValue = "$dayString/$monthString/$yearString"
    }

    /**
     * adds a zero if the day or month is less than 10 (dd/mm/yyyy)
     */
    private fun addFormat(value: Int, _changeString: String): String {

        // make local copy to avoid read-only restriction
        var changeString = _changeString

        // if day or month is less than 10 then add 0
        if (value < 10)
            changeString = "0$changeString"

        return changeString
    }
}