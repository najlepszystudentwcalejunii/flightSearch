package com.example.flightsearchapp.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorite")
    fun getAllFavorites(): List<Favorite>
}