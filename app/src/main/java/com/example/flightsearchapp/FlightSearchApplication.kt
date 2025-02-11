package com.example.flightsearchapp

import android.app.Application
import android.content.Context
import com.example.flightsearchapp.data.AirportDatabase
import com.example.flightsearchapp.data.AirportRepository
import com.example.flightsearchapp.data.DefaultAirportsRepository

interface AppContainer {
    val airportsRepository: AirportRepository
}

class DefaultAppContainer(context: Context): AppContainer {
    override val airportsRepository: AirportRepository by lazy {
        DefaultAirportsRepository(AirportDatabase.getDatabase(context).getAirportDao())
    }
}

class FlightSearchApplication: Application() {

    lateinit var container: DefaultAppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultAppContainer(this)
    }
}