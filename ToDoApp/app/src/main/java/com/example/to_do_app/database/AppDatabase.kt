package com.example.to_do_app.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * (Gures, 2020)
 * https://medium.com/swlh/basic-implementation-of-room-database-with-repository-and-viewmodel-android-jetpack-8945b364d322
 */
@Database(
        entities = [EventModel::class],
        version = 1,
        exportSchema = false
)
/**
 * initialises an instance of the database and returns it
 */
abstract class AppDatabase : RoomDatabase() {

    abstract fun getEventsDao(): EventDao

    companion object {

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "EventDatabase.db"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}