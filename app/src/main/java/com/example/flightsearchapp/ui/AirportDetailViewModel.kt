package com.example.flightsearchapp.ui

import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewModelScope
import com.example.flightsearchapp.FlightSearchApplication
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.data.AirportRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn

class AirportDetailViewModel(
    airportRepository: AirportRepository,
    savedStateHandle: SavedStateHandle
): ViewModel() {
    private val airportId: Int = checkNotNull(savedStateHandle["flightId"])

    val departureAirport: StateFlow<Airport> = airportRepository.getAirportById(airportId).filterNotNull().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = Airport(0,"error", "error", 123)
    )
    val arrivalAirport:StateFlow<List<Airport>> = airportRepository.getArrivalAirports(airportId).stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application =  (this[APPLICATION_KEY] as FlightSearchApplication)
                AirportDetailViewModel(application.container.airportsRepository, this.createSavedStateHandle())
            }
        }
    }
}