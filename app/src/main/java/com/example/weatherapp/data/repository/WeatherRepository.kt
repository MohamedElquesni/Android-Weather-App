package com.example.weatherapp.data.repository

import com.example.weatherapp.data.api.RetrofitClient
import com.example.weatherapp.data.model.WeatherResponse
import com.example.weatherapp.util.Constants

class WeatherRepository {

    // TODO: Get the API service from RetrofitClient

    // TODO: Implement this function to fetch weather data
    // Use Constants.LATITUDE and Constants.LONGITUDE
    // Handle exceptions and return Result<WeatherResponse>
    suspend fun getWeather(): Result<WeatherResponse> {
        TODO("Implement weather fetching")
    }
}
