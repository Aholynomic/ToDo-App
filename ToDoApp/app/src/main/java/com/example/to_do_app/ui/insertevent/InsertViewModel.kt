package com.example.to_do_app.ui.insertevent

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_app.database.AppDatabase
import com.example.to_do_app.database.EventModel
import com.example.to_do_app.database.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class InsertViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository

    var date = ""
    var title = ""
    var startTime = ""
    var endTime = ""
    var desc = ""

    init {
        val eventDB = AppDatabase.getDatabase(application).getEventsDao()
        repository = EventRepository(eventDB)
    }

    // using coroutines to insert data into db
    fun insertEvent(item: EventModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insert(item)
        }
    }

    fun formValidate(): Boolean {
        if (title.isEmpty() || desc.isEmpty()) {
            return false
        }

        return true
    }
}