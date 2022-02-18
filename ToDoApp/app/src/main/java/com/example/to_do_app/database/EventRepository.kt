package com.example.to_do_app.database

/*
* NOTE: not part of the MVC architecture but makes it easier and
* is recommended
 */
class EventRepository(private val eventDao: EventDao) {

    suspend fun insert(item: EventModel) {
        eventDao.insert(item)
    }

    suspend fun delete(item: EventModel) =
            eventDao.delete(item)

    fun getSpecificEvents(date: String) =
            eventDao.getSpecificEvents(date)

    fun getNumberOfEvents(): Int {
        return eventDao.getNumberOfEvents()
    }
}