package com.example.weatherapp.data.model

// TODO: Create data classes that match the API response
// Hint: Use @SerializedName for JSON field mapping

// Main response wrapper
data class WeatherResponse(
    // TODO: Add fields - latitude, longitude, timezone, current, daily, hourly
)

// Current weather data
data class CurrentWeather(
    // TODO: Add fields - temperature_2m, is_day, wind_speed_10m,
    // relative_humidity_2m, rain, pressure_msl, precipitation, apparent_temperature
)

// Daily forecast data
data class DailyWeather(
    // TODO: Add fields - time (List), weather_code (List),
    // temperature_2m_max (List), temperature_2m_min (List)
)

// Hourly data (for UV index)
data class HourlyWeather(
    // TODO: Add fields - time (List), uv_index (List)
)
