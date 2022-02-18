package com.example.to_do_app.database

import androidx.lifecycle.LiveData
import androidx.room.*

/*
 * create functions for db
 */
@Dao
interface EventDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(event: EventModel)

    @Update
    suspend fun delete(event: EventModel)

    @Query("SELECT COUNT(date) FROM Events")
    fun getNumberOfEvents(): Int

    @Query("SELECT * FROM Events WHERE date==:date")
    fun getSpecificEvents(date: String): LiveData<List<EventModel>>
}