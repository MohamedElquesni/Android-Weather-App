package com.example.weatherapp.ui.weather

import com.example.weatherapp.data.model.WeatherResponse

/**
 * State is either loading, success or error.
 */
sealed class WeatherUiState {

    // Loading can be used anywhere, hence Object.
    object Loading : WeatherUiState()

    data class Success(val data: WeatherResponse) : WeatherUiState()

    data class Error(val message: String) : WeatherUiState()
}
