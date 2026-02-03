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

    suspend fun getWeather(latitude: Double, longitude: Double): WeatherResponse {
        return api.getForecast(
            latitude = latitude,
            longitude = longitude,
            daily = "temperature_2m_max,temperature_2m_min,weather_code",
            hourly = "temperature_2m,relative_humidity_2m,apparent_temperature,wind_speed_10m,weather_code,pressure_msl,uv_index,is_day,precipitation_probability",
            timezone = "auto",
            forecastHours = 1
        )
    }
}
