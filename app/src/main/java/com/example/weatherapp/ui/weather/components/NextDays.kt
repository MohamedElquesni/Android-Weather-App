package com.example.weatherapp.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.data.model.Daily
import com.example.weatherapp.ui.theme.onBackgroundHigh
import com.example.weatherapp.ui.theme.onBackgroundMedium
import com.example.weatherapp.util.formatNumber
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun NextDaysSection(
    daily: Daily,
    modifier: Modifier = Modifier
) {
    val times = daily.time ?: emptyList()
    val weatherCodes = daily.weatherCode ?: emptyList()
    val maxTemps = daily.temperatureMax ?: emptyList()
    val minTemps = daily.temperatureMin ?: emptyList()

    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.next_7_days),
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(24.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(24.dp)
                )
                .background(MaterialTheme.colorScheme.surface)
        ) {
            times.forEachIndexed { index, dateString ->
                val dayName = getDayName(dateString)
                DailyRow(
                    dayName = dayName,
                    weatherCode = weatherCodes.getOrNull(index) ?: 0,
                    maxTemp = maxTemps.getOrNull(index)?.toInt() ?: 0,
                    minTemp = minTemps.getOrNull(index)?.toInt() ?: 0
                )
                if (index < times.size - 1) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f))
                    )
                }
            }
        }
    }
}

@Composable
private fun DailyRow(
    dayName: String,
    weatherCode: Int,
    maxTemp: Int,
    minTemp: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(61.dp)
            .padding(horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = dayName,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onBackgroundMedium,
            modifier = Modifier.weight(1f)
        )

        WeatherIcon(
            weatherCode = weatherCode,
            modifier = Modifier.size(32.dp)
        )

        Row(
            modifier = Modifier.weight(1f),
            horizontalArrangement = Arrangement.End,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(R.drawable.ic_arrow_up),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackgroundHigh)
            )
            Text(
                text = "${formatNumber(maxTemp)}${stringResource(R.string.unit_celsius)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackgroundHigh
            )
            Text(
                text = " | ",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.24f)
            )
            Image(
                painter = painterResource(R.drawable.ic_arrow_down),
                contentDescription = null,
                modifier = Modifier.size(12.dp),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackgroundHigh)
            )
            Text(
                text = "${formatNumber(minTemp)}${stringResource(R.string.unit_celsius)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackgroundHigh
            )
        }
    }
}

private fun getDayName(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        date?.let { outputFormat.format(it) } ?: dateString
    } catch (e: Exception) {
        dateString
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF87CEFA)
@Composable
private fun NextDaysSectionPreview() {
    NextDaysSection(
        daily = Daily(
            time = listOf(
                "2026-02-03",
                "2026-02-04",
                "2026-02-05",
                "2026-02-06",
                "2026-02-07",
                "2026-02-08",
                "2026-02-09"
            ),
            weatherCode = listOf(0, 1, 2, 3, 45, 61, 80),
            temperatureMax = listOf(25.0, 26.0, 24.0, 23.0, 22.0, 21.0, 20.0),
            temperatureMin = listOf(18.0, 19.0, 17.0, 16.0, 15.0, 14.0, 13.0)
        )
    )
}

