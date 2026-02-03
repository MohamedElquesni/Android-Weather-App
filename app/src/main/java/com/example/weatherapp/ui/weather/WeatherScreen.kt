package com.example.weatherapp.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.ui.weather.components.CollapsingHeader
import com.example.weatherapp.ui.weather.components.CurrentDayForecast
import com.example.weatherapp.ui.weather.components.HourlySection
import com.example.weatherapp.ui.weather.components.NextDaysSection

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

     when (uiState) {
         is WeatherUiState.Loading -> {
            // I will skip it for now
         }
         is WeatherUiState.Success -> {
             WeatherSuccessContent(state = uiState as WeatherUiState.Success)
         }
         is WeatherUiState.Error -> {
            // I will skip it for now
         }
     }
}

@Composable
fun WeatherSuccessContent(state: WeatherUiState.Success) {
    val weatherData = state.data

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 24.dp, bottom = 32.dp, start = 12.dp, end = 12.dp)
            .background(MaterialTheme.colorScheme.background)
    ) {
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            weatherData.hourly?.let { hourly ->
                item {
                    CollapsingHeader(
                        locationName = "Cairo", // TODO: Get from location service later
                        hourly = hourly,
                        daily = TODO(),
                        collapseFraction = TODO(),
                        modifier = TODO()
                    )
                }

                item {
                    CurrentDayForecast(hourly = hourly)
                }

                item {
                    HourlySection(hourly = hourly)
                }
            }

            weatherData.daily?.let { daily ->
                item {
                    NextDaysSection(daily = daily)
                }
            }

        }
    }
}













