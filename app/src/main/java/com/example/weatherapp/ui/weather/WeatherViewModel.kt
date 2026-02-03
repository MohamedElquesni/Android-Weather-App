package com.example.weatherapp.ui.weather

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.data.location.LocationResult
import com.example.weatherapp.data.location.LocationService
import com.example.weatherapp.data.repository.WeatherRepository
import com.example.weatherapp.util.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationService: LocationService
) : ViewModel() {

    private val _uiState = MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)
    val uiState = _uiState.asStateFlow()

    private var currentLatitude: Double = Constants.LATITUDE
    private var currentLongitude: Double = Constants.LONGITUDE
    private var currentLocationName: String? = null

    init {
        checkLocationAndFetchWeather()
    }

    fun checkLocationAndFetchWeather() {
        viewModelScope.launch {
            if (locationService.hasLocationPermission()) {
                fetchLocationAndWeather()
            } else {
                _uiState.value = WeatherUiState.RequestingPermission
            }
        }
    }

    fun onPermissionResult(granted: Boolean) {
        viewModelScope.launch {
            if (granted) {
                fetchLocationAndWeather()
            } else {
                _uiState.value = WeatherUiState.Error("Location permission is required to show weather for your area")
            }
        }
    }

    private suspend fun fetchLocationAndWeather() {
        _uiState.value = WeatherUiState.Loading

        when (val locationResult = locationService.getCurrentLocation()) {
            is LocationResult.Success -> {
                currentLatitude = locationResult.location.latitude
                currentLongitude = locationResult.location.longitude
                currentLocationName = locationResult.location.cityName
                fetchWeather()
            }
            is LocationResult.PermissionDenied -> {
                _uiState.value = WeatherUiState.Error("Location permission is required to show weather for your area")
            }
            is LocationResult.Error -> {
                _uiState.value = WeatherUiState.Error(locationResult.message)
            }
        }
    }

    fun fetchWeather() {
        viewModelScope.launch {
            _uiState.value = WeatherUiState.Loading
            try {
                val data = repository.getWeather(
                    latitude = currentLatitude,
                    longitude = currentLongitude
                )
                _uiState.value = WeatherUiState.Success(data, currentLocationName)
            } catch (e: Exception) {
                _uiState.value = WeatherUiState.Error(e.message ?: "Failed to load weather")
            }
        }
    }
}
