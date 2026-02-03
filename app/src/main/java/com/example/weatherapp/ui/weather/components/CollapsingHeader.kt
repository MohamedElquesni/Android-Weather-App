package com.example.weatherapp.ui.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Daily
import com.example.weatherapp.data.model.Hourly
import com.example.weatherapp.ui.theme.onBackgroundMedium
import com.example.weatherapp.util.getWeatherDescription
import com.example.weatherapp.util.formatNumber

@Composable
fun CollapsingHeader(
    locationName: String,
    hourly: Hourly,
    daily: Daily,
    collapseFraction: Float,
    modifier: Modifier = Modifier,
) {
    val weatherCode = hourly.weatherCode?.firstOrNull() ?: 0

    val iconWidth = lerp(220.dp, 124.dp, collapseFraction)
    val iconHeight = lerp(200.dp, 112.dp, collapseFraction)

    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Title(locationName = locationName)

        Spacer(modifier = Modifier.height(12.dp))

        if (collapseFraction < 0.5f) {
            // Expanded layout - vertical
            WeatherIcon(
                weatherCode = weatherCode,
                modifier = Modifier.size(iconWidth, iconHeight)
            )

            Spacer(modifier = Modifier.height(12.dp))

            TemperatureInfo(
                hourly = hourly,
                daily = daily,
                modifier = Modifier.size(height = 143.dp, width = 168.dp)
            )
        } else {
            // Collapsed layout - horizontal
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                WeatherIcon(
                    weatherCode = weatherCode,
                    modifier = Modifier.size(iconWidth, iconHeight)
                )

                TemperatureInfo(
                    hourly = hourly,
                    daily = daily,
                    modifier = Modifier.size(height = 143.dp, width = 168.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun CollapsingHeaderExpandedPreview() {
    CollapsingHeader(
        locationName = "Cairo",
        collapseFraction = 0f,
        hourly = Hourly(
            time = listOf("2026-02-02T20:00"),
            temperature2m = listOf(25.0),
            relativeHumidity2m = listOf(50),
            apparentTemperature = listOf(27.0),
            windSpeed10m = listOf(10.0),
            weatherCode = listOf(0),
            pressureMsl = listOf(1013.0),
            precipitationProbability = listOf(0.0),
            uvIndex = listOf(0.0),
            isDay = listOf(1)
        ),
        daily = Daily(
            time = listOf("2026-02-02"),
            weatherCode = listOf(0),
            temperatureMax = listOf(25.0),
            temperatureMin = listOf(18.0)
        )
    )
}

@Preview(showBackground = true)
@Composable
private fun CollapsingHeaderCollapsedPreview() {
    CollapsingHeader(
        locationName = "Cairo",
        collapseFraction = 1f,
        hourly = Hourly(
            time = listOf("2026-02-02T20:00"),
            temperature2m = listOf(25.0),
            relativeHumidity2m = listOf(50),
            apparentTemperature = listOf(27.0),
            windSpeed10m = listOf(10.0),
            weatherCode = listOf(2),
            pressureMsl = listOf(1013.0),
            precipitationProbability = listOf(0.0),
            uvIndex = listOf(0.0),
            isDay = listOf(1)
        ),
        daily = Daily(
            time = listOf("2026-02-02"),
            weatherCode = listOf(2),
            temperatureMax = listOf(25.0),
            temperatureMin = listOf(18.0)
        )
    )
}

@Composable
fun TemperatureInfo(
    hourly: Hourly,
    daily: Daily,
    modifier: Modifier = Modifier,
) {
    val temperature = hourly.temperature2m?.firstOrNull()?.toInt() ?: 0
    val weatherCode = hourly.weatherCode?.firstOrNull() ?: 0
    val maxTemp = daily.temperatureMax?.firstOrNull()?.toInt() ?: 0
    val minTemp = daily.temperatureMin?.firstOrNull()?.toInt() ?: 0

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "${formatNumber(temperature)}${stringResource(R.string.unit_celsius)}",
            color = MaterialTheme.colorScheme.onBackground,
            style = MaterialTheme.typography.displayLarge
        )

        Text(
            text = getWeatherDescription(weatherCode),
            color = MaterialTheme.colorScheme.onBackgroundMedium,
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(12.dp))

        MinMaxTemperature(
            maxTemp = maxTemp,
            minTemp = minTemp,
            modifier = Modifier
                .fillMaxWidth()
                .height(35.dp)
                .clip(RoundedCornerShape(32.dp))
                .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f))
                .padding(horizontal = 24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TemperatureInfoPreview() {
    TemperatureInfo(
        hourly = Hourly(
            time = listOf("2026-02-02T20:00"),
            temperature2m = listOf(25.0),
            relativeHumidity2m = listOf(50),
            apparentTemperature = listOf(27.0),
            windSpeed10m = listOf(10.0),
            weatherCode = listOf(2),
            pressureMsl = listOf(1013.0),
            precipitationProbability = listOf(0.0),
            uvIndex = listOf(0.0),
            isDay = listOf(1)
        ),
        daily = Daily(
            time = listOf("2026-02-02"),
            weatherCode = listOf(2),
            temperatureMax = listOf(25.0),
            temperatureMin = listOf(18.0)
        )
    )
}


