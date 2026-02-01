package com.example.weatherapp.ui.weather

import com.example.weatherapp.ui.weather.components.Title
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

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
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Spacer(modifier = Modifier.size(24.dp))

        Title()

        CollapsingHeader()
    }
}

@Composable
fun CollapsingHeader() {
    TODO("Not yet implemented")
}




