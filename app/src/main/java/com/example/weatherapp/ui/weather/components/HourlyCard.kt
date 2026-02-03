package com.example.weatherapp.ui.weather.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.onBackgroundHigh
import com.example.weatherapp.ui.theme.onBackgroundMedium
import com.example.weatherapp.util.formatNumber

@Composable
fun HourlyCard(
    hour: String,
    temperature: Int,
    weatherCode: Int,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier) {
        // Card background (clipped separately)
        Box(
            modifier = Modifier
                .fillMaxSize()
                .clip(RoundedCornerShape(20.dp))
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f),
                    shape = RoundedCornerShape(20.dp)
                )
                .background(MaterialTheme.colorScheme.surface.copy(alpha = 0.7f))

        )

        // Content
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(2.dp),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 62.dp)
        ) {
            Text(
                text = "${formatNumber(temperature)}${stringResource(R.string.unit_celsius)}",
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackgroundHigh
            )

            Text(
                text = hour,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onBackgroundMedium
            )
        }

        // Icon (outside clipped area, can extend beyond card)
        WeatherIcon(
            weatherCode = weatherCode,
            modifier = Modifier
                .size(height = 58.dp, width = 64.dp)
                .offset(x = 12.dp, y = (-11).dp)
        )
    }
}

@Preview(showBackground = true, backgroundColor = 0xFF87CEFA)
@Composable
private fun HourlyCardPreview() {
    HourlyCard(
        hour = "14:00",
        temperature = 25,
        weatherCode = 4,
        modifier = Modifier.size(width = 88.dp, height = 120.dp)
    )
}
