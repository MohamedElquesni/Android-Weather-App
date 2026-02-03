package com.example.weatherapp.ui.weather.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.R
import com.example.weatherapp.ui.theme.onBackgroundMedium

@Composable
fun MinMaxTemperature(
    maxTemp: Int,
    minTemp: Int,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Image(
            painter = painterResource(R.drawable.ic_arrow_up),
            contentDescription = "Max temperature",
            modifier = Modifier.size(12.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackgroundMedium)
        )
        Text(
            text = "${maxTemp}°C",
            color = MaterialTheme.colorScheme.onBackgroundMedium,
            style = MaterialTheme.typography.bodyMedium
        )

        Text(
            text = "|",
            color = MaterialTheme.colorScheme.onBackground.copy(alpha = 0.24f),
            style = MaterialTheme.typography.bodyMedium
        )

        Image(
            painter = painterResource(R.drawable.ic_arrow_down),
            contentDescription = "Min temperature",
            modifier = Modifier.size(12.dp),
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onBackgroundMedium)
        )

        Text(
            text = "${minTemp}°C",
            color = MaterialTheme.colorScheme.onBackgroundMedium,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MinMaxTemperaturePreview() {
    MinMaxTemperature(
        maxTemp = 25,
        minTemp = 18,
        modifier = Modifier
            .height(35.dp)
            .clip(RoundedCornerShape(32.dp))
            .background(MaterialTheme.colorScheme.onBackground.copy(alpha = 0.08f))
            .padding(horizontal = 24.dp)
    )
}
