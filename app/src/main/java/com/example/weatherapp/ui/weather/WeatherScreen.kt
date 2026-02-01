package com.example.weatherapp.ui.weather

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

     when (uiState) {
         is WeatherUiState.Loading -> {

         }
         is WeatherUiState.Success -> {

         }
         is WeatherUiState.Error -> {

         }
     }
}
