package com.example.weatherapp.ui.weather

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.weatherapp.R
import com.example.weatherapp.ui.weather.components.CollapsingHeader
import com.example.weatherapp.ui.weather.components.CurrentDayForecast
import com.example.weatherapp.ui.weather.components.HourlySection
import com.example.weatherapp.ui.weather.components.NextDaysSection

@Composable
fun WeatherScreen(
    viewModel: WeatherViewModel = viewModel(factory = WeatherViewModel.Factory)
) {
    val uiState by viewModel.uiState.collectAsState()

    when (uiState) {
        is WeatherUiState.Loading -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is WeatherUiState.Success -> {
            WeatherSuccessContent(state = uiState as WeatherUiState.Success)
        }
        is WeatherUiState.Error -> {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val errorState = uiState as WeatherUiState.Error
                val errorMessage = if (errorState.message == "Failed to load weather") {
                    stringResource(R.string.error_loading_weather)
                } else {
                    errorState.message
                }
                Text(
                    text = errorMessage,
                    color = MaterialTheme.colorScheme.error
                )
            }
        }
    }
}

@Composable
fun WeatherSuccessContent(state: WeatherUiState.Success) {
    val weatherData = state.data
    val hourly = weatherData.hourly
    val daily = weatherData.daily

    val listState = rememberLazyListState()

    // Snap: if scrolled more than 20px, collapse fully
    val collapseFraction by remember {
        derivedStateOf {
            val firstItem = listState.layoutInfo.visibleItemsInfo.firstOrNull()
            if (firstItem == null || firstItem.index != 0) {
                1f
            } else {
                if (-firstItem.offset > 20) 1f else 0f
            }
        }
    }

    val gradientBrush = Brush.verticalGradient(
        colors = listOf(
            MaterialTheme.colorScheme.background,
            MaterialTheme.colorScheme.surfaceVariant
        )
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
            .padding(top = 24.dp, bottom = 32.dp, start = 12.dp, end = 12.dp)
    ) {
        LazyColumn(
            state = listState,
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            if (hourly != null && daily != null) {
                item {
                    CollapsingHeader(
                        locationName = stringResource(R.string.location_cairo),
                        hourly = hourly,
                        daily = daily,
                        collapseFraction = collapseFraction
                    )
                }

                item {
                    CurrentDayForecast(hourly = hourly)
                }

                item {
                    HourlySection(hourly = hourly)
                }

                item {
                    NextDaysSection(daily = daily)
                }
            }
        }
    }
}









