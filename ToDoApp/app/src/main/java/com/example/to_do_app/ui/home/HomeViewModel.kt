package com.example.to_do_app.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.to_do_app.database.AppDatabase
import com.example.to_do_app.database.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

private var count: Int = 0

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: EventRepository

    init {
        val eventDB = AppDatabase.getDatabase(application).getEventsDao()
        repository = EventRepository(eventDB)
    }

    /**
     * grab the overall number of events
     */
    fun getNumber() {
        viewModelScope.launch(Dispatchers.IO) {
            count = repository.getNumberOfEvents()
        }
    }

    /**
     * Display total number of events
     */
    private val _text = MutableLiveData<String>().apply {
        value = "Overall, you have $count events"
    }
    val text: LiveData<String> = _text
}