package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAllAirports(): Flow<List<Airport>>
    fun getAirportsByCode(code: String): Flow<List<Airport>>
    fun getAirportsByName(name: String): Flow<List<Airport>>
}

class DefaultAirportsRepository(private val airportDao: AirportDao): AirportRepository {
    override fun getAllAirports(): Flow<List<Airport>> {
        return airportDao.getAllAirports()
    }

    override fun getAirportsByCode(code: String): Flow<List<Airport>> {
        return airportDao.getAirportsByCode(code)
    }

    override fun getAirportsByName(name: String): Flow<List<Airport>> {
        return airportDao.getAirportsByName(name)
    }
}