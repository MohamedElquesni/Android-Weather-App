package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.model.WeatherResponse

class WeatherRepository(
    private val api: WeatherApiService
) {

    suspend fun getWeather(
        latitude: Double,
        longitude: Double,
        daily: String,
        hourly: String,
        current: String,
        timezone: String,
        forecastHours: Int
    ): WeatherResponse {
        return api.getForecast(
            latitude = latitude,
            longitude = longitude,
            daily = daily,
            hourly = hourly,
            current = current,
            timezone = timezone,
            forecastHours = forecastHours
        )
    }
}
