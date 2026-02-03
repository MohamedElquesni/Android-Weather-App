package com.example.weatherapp.util

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R

@Composable
fun getWeatherDescription(weatherCode: Int): String {
    return when (weatherCode) {
        0 -> stringResource(R.string.weather_clear_sky)
        1 -> stringResource(R.string.weather_mainly_clear)
        2 -> stringResource(R.string.weather_partly_cloudy)
        3 -> stringResource(R.string.weather_overcast)
        45 -> stringResource(R.string.weather_fog)
        48 -> stringResource(R.string.weather_depositing_rime_fog)
        51 -> stringResource(R.string.weather_light_drizzle)
        53 -> stringResource(R.string.weather_moderate_drizzle)
        55 -> stringResource(R.string.weather_dense_drizzle)
        56 -> stringResource(R.string.weather_light_freezing_drizzle)
        57 -> stringResource(R.string.weather_dense_freezing_drizzle)
        61 -> stringResource(R.string.weather_slight_rain)
        63 -> stringResource(R.string.weather_moderate_rain)
        65 -> stringResource(R.string.weather_heavy_rain)
        66 -> stringResource(R.string.weather_light_freezing_rain)
        67 -> stringResource(R.string.weather_heavy_freezing_rain)
        71 -> stringResource(R.string.weather_slight_snow_fall)
        73 -> stringResource(R.string.weather_moderate_snow_fall)
        75 -> stringResource(R.string.weather_heavy_snow_fall)
        77 -> stringResource(R.string.weather_snow_grains)
        80 -> stringResource(R.string.weather_slight_rain_showers)
        81 -> stringResource(R.string.weather_moderate_rain_showers)
        82 -> stringResource(R.string.weather_violent_rain_showers)
        85 -> stringResource(R.string.weather_slight_snow_showers)
        86 -> stringResource(R.string.weather_heavy_snow_showers)
        95 -> stringResource(R.string.weather_thunderstorm)
        96 -> stringResource(R.string.weather_thunderstorm_slight_hail)
        99 -> stringResource(R.string.weather_thunderstorm_heavy_hail)
        else -> stringResource(R.string.weather_unknown)
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
