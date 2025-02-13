package com.example.flightsearchapp.data

import kotlinx.coroutines.flow.Flow

interface AirportRepository {
    fun getAllAirports(): Flow<List<Airport>>
    fun getAirportsByName(name: String): Flow<List<Airport>>
    fun getAirportById(id: Int): Flow<Airport>
    fun getArrivalAirports(id: Int): Flow<List<Airport>>
}

class DefaultAirportsRepository(private val airportDao: AirportDao): AirportRepository {
    override fun getAllAirports(): Flow<List<Airport>> {
        return airportDao.getAllAirports()
    }

    override fun getArrivalAirports(id: Int): Flow<List<Airport>> {
        return airportDao.getAirportsNotOnRoute(id)
    }

    override fun getAirportsByName(name: String): Flow<List<Airport>> {
        return airportDao.getAirportsByName(name)
    }

    override fun getAirportById(id: Int): Flow<Airport> {
        return airportDao.getAirportById(id)
    }
}