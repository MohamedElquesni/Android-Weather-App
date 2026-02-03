package com.example.weatherapp.ui.weather.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Hourly
import com.example.weatherapp.ui.theme.onBackgroundHigh
import com.example.weatherapp.ui.theme.onBackgroundMedium

@Composable
fun CurrentDayForecast(
    hourly: Hourly,
    modifier: Modifier = Modifier
) {
    val wind = hourly.windSpeed10m?.firstOrNull() ?: 0.0
    val humidity = hourly.relativeHumidity2m?.firstOrNull() ?: 0
    val rainChance = hourly.precipitationProbability?.firstOrNull() ?: 0
    val uvIndex = hourly.uvIndex?.firstOrNull() ?: 0.0
    val pressure = hourly.pressureMsl?.firstOrNull() ?: 0.0
    val feelsLike = hourly.apparentTemperature?.firstOrNull() ?: 0.0

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(6.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(6.dp)
        ) {
            StatCard(
                icon = R.drawable.ic_wind,
                value = "${wind.toInt()} km/h",
                label = "Wind",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = R.drawable.ic_humidity,
                value = "$humidity%",
                label = "Humidity",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = R.drawable.ic_rain,
                value = "$rainChance%",
                label = "Rain",
                modifier = Modifier.weight(1f)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            StatCard(
                icon = R.drawable.ic_uv,
                value = "${uvIndex.toInt()}",
                label = "UV Index",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = R.drawable.ic_pressure,
                value = "${pressure.toInt()} hPa",
                label = "Pressure",
                modifier = Modifier.weight(1f)
            )
            StatCard(
                icon = R.drawable.ic_temperature,
                value = "${feelsLike.toInt()}°C",
                label = "Feels Like",
                modifier = Modifier.weight(1f)
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF87CEFA)
@Composable
private fun CurrentDayForecastPreview(
) {
    CurrentDayForecast(
        hourly = Hourly(
            time = listOf("2026-02-02T20:00"),
            temperature2m = listOf(25.0),
            relativeHumidity2m = listOf(65),
            apparentTemperature = listOf(27.0),
            windSpeed10m = listOf(13.0),
            weatherCode = listOf(2),
            pressureMsl = listOf(1013.0),
            precipitationProbability = listOf(0.0),
            uvIndex = listOf(5.0),
            isDay = listOf(1)
        )
    )
}
@Composable
fun StatCard(
    @DrawableRes icon: Int,
    value: String,
    label: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = modifier
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))
            .padding(vertical = 16.dp)
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = label,
            modifier = Modifier.size(32.dp)
        )

        Column(
            verticalArrangement = Arrangement.spacedBy(2.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = value,
                color = MaterialTheme.colorScheme.onBackgroundHigh,
                style = MaterialTheme.typography.titleMedium
            )

            Text(
                text = label,
                color = MaterialTheme.colorScheme.onBackgroundMedium,
                style = MaterialTheme.typography.bodyMedium
            )
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF87CEFA)
@Composable
private fun StatCardPreview() {
    StatCard(
        icon = R.drawable.ic_wind,
        value = "13 km/h",
        label = "Wind"
    )
}
