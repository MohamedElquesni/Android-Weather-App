package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    // TODO: Implement the GET request
    // Endpoint: forecast
    // Query parameters: latitude, longitude, daily, hourly, current, timezone, forecast_hours
    // Return type: WeatherResponse

    @GET("forecast")
    suspend fun getWeather(
        // TODO: Add @Query parameters
    ): WeatherResponse
}
