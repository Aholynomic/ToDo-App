package com.example.to_do_app.ui.edit

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.to_do_app.database.AppDatabase
import com.example.to_do_app.database.EventModel
import com.example.to_do_app.database.EventRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * (GeeksforGeeks, 2021)
 * https://www.geeksforgeeks.org/how-to-build-a-grocery-android-app-using-mvvm-and-room-database/
 */
class EditViewModel(application: Application) : AndroidViewModel(application) {

    // initialise repository
    private val repository: EventRepository

    init {
        // get DAO
        val eventDB = AppDatabase.getDatabase(application).getEventsDao()
        // give to repository
        repository = EventRepository(eventDB)
    }

    // filter DB for specific event
    fun specificEvents(item: String) = repository.getSpecificEvents(item)

    // delete entry in rvList
    fun delete(item: EventModel) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(item)
        }
    }
}