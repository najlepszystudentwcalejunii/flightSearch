package com.example.flightsearchapp.data

import androidx.room.Dao
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface AirportDao {
    @Query("SELECT * FROM airport")
    fun getAllAirports(): Flow<List<Airport>>

    @Query("SELECT * FROM airport WHERE iata_code like :code")
    fun getAirportsByCode(code: String): Flow<List<Airport>>

    @Query("Select * from airport where name like :name")
    fun getAirportsByName(name: String): Flow<List<Airport>>

}