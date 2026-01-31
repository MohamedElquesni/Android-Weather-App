package com.example.weatherapp.data.model

import com.google.gson.annotations.SerializedName

data class WeatherResponse(
    val current: Current?,
    val hourly: Hourly?,
    val daily: Daily?
)

data class Current(
    @SerializedName("temperature_2m")
    val temperature2m: Double?,
    @SerializedName("apparent_temperature")
    val apparentTemperature: Double?,
    @SerializedName("is_day")
    val isDay: Int?,
    @SerializedName("wind_speed_10m")
    val windSpeed10m: Double?,
    @SerializedName("relative_humidity_2m")
    val relativeHumidity2m: Int?,
    val rain: Double?,
    val precipitation: Double?,
    @SerializedName("pressure_msl")
    val pressureMsl: Double?
)

data class Hourly(
    @SerializedName("uv_index")
    val uvIndex: List<Double>?
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
