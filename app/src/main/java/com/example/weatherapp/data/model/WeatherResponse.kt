package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val hourly: Hourly?,
    val daily: Daily?
)

data class Hourly(
    val time: List<String>?,
    @SerializedName("temperature_2m")
    val temperature2m: List<Double>?,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: List<Int>?,
    @SerializedName("apparent_temperature")
    val apparentTemperature: List<Double>?,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: List<Double>?,
    @SerializedName("weather_code")
    val weatherCode: List<Int>?,
    @SerializedName("pressure_msl")
    val pressureMsl: List<Double>?,
    @SerializedName("uv_index")
    val uvIndex: List<Double>?,
    @SerializedName("precipitation_probability")
    val precipitationProbability: List<Double>,
    @SerializedName("is_day")
    val isDay: List<Int>?
)

data class Daily(
    val time: List<String>?,
    @SerializedName("weather_code")
    val weatherCode: List<Int>?,
    @SerializedName("temperature_2m_max")
    val temperatureMax: List<Double>?,
    @SerializedName("temperature_2m_min")
    val temperatureMin: List<Double>?
)
