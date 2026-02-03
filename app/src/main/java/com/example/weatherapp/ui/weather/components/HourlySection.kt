package com.example.weatherapp.ui.weather.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Hourly
import com.example.weatherapp.util.convertToArabicIndic
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun HourlySection(
    hourly: Hourly,
    modifier: Modifier = Modifier
) {
    val times = hourly.time ?: emptyList()
    val temps = hourly.temperature2m ?: emptyList()
    val weatherCodes = hourly.weatherCode ?: emptyList()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        Text(
            text = stringResource(R.string.today),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        LazyRow(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            contentPadding = PaddingValues(start = 12.dp, end = 0.dp)
        ) {
            itemsIndexed(times) { index, time ->
                val hour = formatHour(time)
                HourlyCard(
                    hour = hour,
                    temperature = temps.getOrNull(index)?.toInt() ?: 0,
                    weatherCode = weatherCodes.getOrNull(index) ?: 0,
                    modifier = Modifier.size(width = 88.dp, height = 120.dp)
                )
            }
        }
    }
}

private fun formatHour(timeString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm", Locale.getDefault())
        val outputFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
        val date = inputFormat.parse(timeString)
        val formatted = date?.let { outputFormat.format(it) } ?: timeString.substringAfter("T").substringBefore(":") + ":00"
        
        if (Locale.getDefault().language == "ar") {
            convertToArabicIndic(formatted)
        } else {
            formatted
        }
    } catch (e: Exception) {
        val fallback = timeString.substringAfter("T").substringBefore(":") + ":00"
        if (Locale.getDefault().language == "ar") {
            convertToArabicIndic(fallback)
        } else {
            fallback
        }
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF87CEFA)
@Composable
private fun HourlySectionPreview() {
    HourlySection(
        hourly = Hourly(
            time = listOf("2026-02-03T00:00", "2026-02-03T01:00", "2026-02-03T02:00", "2026-02-03T03:00"),
            temperature2m = listOf(25.0, 24.0, 23.0, 22.0),
            relativeHumidity2m = listOf(50, 52, 54, 56),
            apparentTemperature = listOf(27.0, 26.0, 25.0, 24.0),
            windSpeed10m = listOf(10.0, 11.0, 12.0, 13.0),
            weatherCode = listOf(0, 1, 2, 3),
            pressureMsl = listOf(1013.0, 1013.0, 1012.0, 1012.0),
            uvIndex = listOf(0.0, 0.0, 0.0, 0.0),
            precipitationProbability = listOf(0.0, 0.0, 10.0, 20.0),
            isDay = listOf(0, 0, 0, 0)
        )
    )
}