package com.example.flightsearchapp.ui

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.Airport

@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: AirportDetailViewModel = viewModel(factory = AirportDetailViewModel.Factory)
) {
    val departureAirport = viewModel.departureAirport.collectAsState()
    val arrivalAirport = viewModel.arrivalAirport.collectAsState()
    FlightList(departureAirport.value, arrivalAirport.value)
}

@Composable
fun FlightList(departure: Airport, destinations: List<Airport>) {
    Text(text = stringResource(id = R.string.flights_from, departure.name))
    LazyColumn {
        items(destinations) {
            FlightCard(departure, it)
        }
    }
}

@Composable
fun FlightCard(departure: Airport, destination: Airport) {
    Card(modifier = Modifier.fillMaxWidth().padding(4.dp)) {
        Column {
            Text(stringResource(id = R.string.depart), style = MaterialTheme.typography.titleMedium)
            FlightInfo(departure)
            Text(stringResource(id = R.string.arrive), style = MaterialTheme.typography.titleMedium)
            FlightInfo(destination)
        }
    }
}

@Composable
fun FlightInfo(airport: Airport) {
    Row(verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
        Text(text = airport.code, style = MaterialTheme.typography.titleMedium)
        Spacer(modifier = Modifier.width(10.dp))
        Text(text = airport.name, style = MaterialTheme.typography.bodyMedium)
    }
}

@Preview(showBackground = true, uiMode = Configuration.UI_MODE_NIGHT_YES)
@Composable
fun FlightCardPreview() {
    MaterialTheme {
        Surface {
            FlightCard(
                Airport(1, "Test", "TST", 2354123),
                Airport(2, "Test2", "TST2", 2354123)
            )

        }
    }
}