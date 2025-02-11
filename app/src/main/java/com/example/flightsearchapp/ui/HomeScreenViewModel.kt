package com.example.flightsearchapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.flightsearchapp.FlightSearchApplication
import com.example.flightsearchapp.data.Airport
import com.example.flightsearchapp.data.AirportRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val airportRepository: AirportRepository) : ViewModel() {
    val _uiState = MutableStateFlow(HomeScreenUiState())
    val uiState:StateFlow<HomeScreenUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            airportRepository.getAllAirports().collect {
                _uiState.value = HomeScreenUiState(it)
            }
        }
    }

    fun updateSearchQuery(query: String) {
        _uiState.value = _uiState.value.copy(searchQuery = query)
    }

    fun searchAirportByName(name: String) {
        viewModelScope.launch {
            airportRepository.getAirportsByName("$name%").collect {
                _uiState.value = HomeScreenUiState(it, name)
            }
        }
    }

    companion object {
        val Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as FlightSearchApplication)
                HomeScreenViewModel(application.container.airportsRepository)
            }
        }
    }
}
data class HomeScreenUiState(
    val airportList: List<Airport> = emptyList(),
    val searchQuery: String = ""
)