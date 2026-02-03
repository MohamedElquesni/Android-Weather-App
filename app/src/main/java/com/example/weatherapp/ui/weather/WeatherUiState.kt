package com.example.weatherapp.ui.weather

import com.example.weatherapp.data.model.WeatherResponse

sealed class WeatherUiState {
    data object Loading : WeatherUiState()
    data object RequestingPermission : WeatherUiState()
    data class Success(val data: WeatherResponse, val locationName: String?) : WeatherUiState()
    data class Error(val message: String) : WeatherUiState()
}
