package com.example.flightsearchapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.flightsearchapp.R
import com.example.flightsearchapp.data.Airport
import kotlin.math.sin

@Composable
fun HomeScreen(
    viewModel: HomeScreenViewModel = viewModel(factory = HomeScreenViewModel.Factory),
    modifier: Modifier = Modifier
) {
    val uiState = viewModel.uiState.collectAsState()
    Column(modifier = modifier) {
        OutlinedTextField(
            value = uiState.value.searchQuery,
            onValueChange = {
                viewModel.updateSearchQuery(it)
                            viewModel.searchAirportByName(it)},
            shape = MaterialTheme.shapes.extraLarge,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_search_24),
                    contentDescription = ""/*TODO*/
                )
            },
            singleLine = true,
            modifier = Modifier.fillMaxWidth().padding(4.dp),
        )
        AirportList(airports = uiState.value.airportList)
    }
}

@Composable
fun AirportList(
    airports: List<Airport>,
) {
    LazyColumn() {
        items(airports) {
            Airport(airport = it)
        }
    }
}

@Composable
fun Airport(airport: Airport) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp)
    ) {
        Row(Modifier.padding(6.dp), verticalAlignment = androidx.compose.ui.Alignment.CenterVertically) {
            Text(text = airport.code, style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.size(width = 10.dp, height = 0.dp))
            Text(text = airport.name)
        }

    }
}

@Preview(showBackground = true)
@Composable
fun AirportCardPreview() {
    Airport(Airport(1, "Test", "TST", 2354123))
}