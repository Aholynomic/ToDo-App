package com.example.to_do_app.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


/**
 * data class which stores data for every column in db
 */
@Entity(tableName = "Events")
data class EventModel(
        @PrimaryKey(autoGenerate = true)
        var ID: Int,

        @ColumnInfo(name = "title")
        var title: String,

        @ColumnInfo(name = "date")
        var date: String,

        @ColumnInfo(name = "time_start")
        var timeStart: String,

        @ColumnInfo(name = "time_end")
        var timeEnd: String,

        @ColumnInfo(name = "description")
        var description: String

)