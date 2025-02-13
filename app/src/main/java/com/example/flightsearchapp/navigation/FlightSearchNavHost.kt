package com.example.flightsearchapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.flightsearchapp.ui.DetailsScreen
import com.example.flightsearchapp.ui.HomeScreen

@Composable
fun FlightSearchNavHost(
    navController: NavHostController,
    startDestination: String = FlightSearchScreens.Home.name,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier
    ) {
        composable(route = FlightSearchScreens.Home.name) {
            HomeScreen(
                onAirportClick = {
                    navController.navigate("${FlightSearchScreens.Details.name}/${it}")
                }
            )
        }
        composable(
            route = FlightSearchScreens.Details.name + "/{flightId}",
            arguments = listOf(navArgument("flightId") {
                type = NavType.IntType
            })
        ) {
            DetailsScreen()
        }
    }
}