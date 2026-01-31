package com.example.weatherapp.data.api

import com.example.weatherapp.data.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApiService {

    @GET("v1/forecast")
    suspend fun getForecast(
        @Query("latitude") latitude: Double,
        @Query("longitude") longitude: Double,

        @Query("daily") daily: String,
        @Query("hourly") hourly: String,
        @Query("current") current: String,

        @Query("timezone") timezone: String = "auto",
        @Query("forecast_hours") forecastHours: Int = 1
    ): WeatherResponse
}
