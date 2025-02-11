package com.example.flightsearchapp.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Airport::class, Favorite::class], version = 1, exportSchema = false)
abstract class AirportDatabase: RoomDatabase() {
    abstract fun getAirportDao(): AirportDao
    abstract fun getFavouriteDao(): FavoriteDao

    companion object {
        @Volatile
        private var INSTANCE: AirportDatabase? = null

        fun getDatabase(context: Context): AirportDatabase {
            return INSTANCE ?: synchronized(this) {
                Room.databaseBuilder(
                    context,
                    AirportDatabase::class.java,
                    "airport_database"
                ).createFromAsset("database/flight_search.db")
                    .fallbackToDestructiveMigration()
                    .build().also { INSTANCE = it }
            }
        }
    }
}