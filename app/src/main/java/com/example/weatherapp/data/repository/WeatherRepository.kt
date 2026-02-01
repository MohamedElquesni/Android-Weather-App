package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.WeatherApiService
import com.example.weatherapp.data.model.WeatherResponse

/**
 * I decided that it is more obvious to handle errors at the view model layer.
 * If there is error, view model turns the ui state into error
 */
/*
Note : Normally, it is recommended that there exists Repository interface,
and under it there exists wanted classes (remote, local, etc.)

However, this step was skipped since Aziza does not need require any other source
 */
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
