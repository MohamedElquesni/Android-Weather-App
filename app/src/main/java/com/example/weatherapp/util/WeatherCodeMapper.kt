package com.example.weatherapp.util

import androidx.annotation.DrawableRes
import com.example.weatherapp.R

fun getWeatherDescription(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> "Clear Sky"
        1 -> "Mainly Clear"
        2 -> "Partly Cloudy"
        3 -> "Overcast"
        45 -> "Fog"
        48 -> "Depositing Rime Fog"
        51 -> "Light Drizzle"
        53 -> "Moderate Drizzle"
        55 -> "Dense Drizzle"
        56 -> "Light Freezing Drizzle"
        57 -> "Dense Freezing Drizzle"
        61 -> "Slight Rain"
        63 -> "Moderate Rain"
        65 -> "Heavy Rain"
        66 -> "Light Freezing Rain"
        67 -> "Heavy Freezing Rain"
        71 -> "Slight Snow Fall"
        73 -> "Moderate Snow Fall"
        75 -> "Heavy Snow Fall"
        77 -> "Snow Grains"
        80 -> "Slight Rain Showers"
        81 -> "Moderate Rain Showers"
        82 -> "Violent Rain Showers"
        85 -> "Slight Snow Showers"
        86 -> "Heavy Snow Showers"
        95 -> "Thunderstorm"
        96 -> "Thunderstorm with Slight Hail"
        99 -> "Thunderstorm with Heavy Hail"
        else -> "Unknown"
    }
}

@DrawableRes
fun getWeatherIcon(weatherCode: Int): Int {
    return when (weatherCode) {
        0 -> R.drawable.ic_clear_sky
        1 -> R.drawable.ic_mainly_clear
        2 -> R.drawable.ic_partly_cloudy
        3 -> R.drawable.ic_overcast
        45 -> R.drawable.ic_fog
        48 -> R.drawable.ic_depositing_rime_fog
        51 -> R.drawable.ic_drizzle_light
        53 -> R.drawable.ic_drizzle_moderate
        55 -> R.drawable.ic_drizzle_dense
        56 -> R.drawable.ic_freezing_drizzle_light
        57 -> R.drawable.ic_freezing_drizzle_dense
        61 -> R.drawable.ic_rain_slight
        63 -> R.drawable.ic_rain_moderate
        65 -> R.drawable.ic_rain_heavy
        66 -> R.drawable.ic_freezing_rain_light
        67 -> R.drawable.ic_freezing_rain_heavy
        71 -> R.drawable.ic_snow_fall_slight
        73 -> R.drawable.ic_snow_fall_moderate
        75 -> R.drawable.ic_snow_fall_heavy
        77 -> R.drawable.ic_snow_grains
        80 -> R.drawable.ic_rain_showers_slight
        81 -> R.drawable.ic_rain_showers_moderate
        82 -> R.drawable.ic_rain_showers_violent
        85 -> R.drawable.ic_snow_shower_slight
        86 -> R.drawable.ic_snow_shower_heavy
        95 -> R.drawable.ic_thunderstorm
        96 -> R.drawable.ic_thunderstorm_slight_hail
        99 -> R.drawable.ic_thunderstorm_heavy_hail
        else -> R.drawable.ic_clear_sky
    }
}
