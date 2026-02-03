package com.example.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.api.RetrofitClient
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.util.Constants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val repository: WeatherRepository
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        fetchWeather()
    }

    fun fetchWeather() {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val data = repository.getWeather(
                    latitude = Constants.LATITUDE,
                    longitude = Constants.LONGITUDE
                )
                _uiState.value = WeatherUiState.Success(data)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Failed to load weather")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return WeatherViewModel(
                    WeatherRepository(RetrofitClient.weatherApi)
                ) as T
            }
        }
    }
}
