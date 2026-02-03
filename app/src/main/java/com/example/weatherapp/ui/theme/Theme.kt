package com.example.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

// Alpha variants
val ColorScheme.onBackgroundHigh: Color
    get() = onBackground.copy(alpha = 0.87f)

val ColorScheme.onBackgroundMedium: Color
    get() = onBackground.copy(alpha = 0.6f)

private val DarkColorScheme = darkColorScheme(
    primary = SkyBlue,                    // constant blue (today's stats)
    onBackground = PureWhite,             // main text
    onSurfaceVariant = PureWhite,         // location text
    surface = NightBlack,                 // card color
    background = NightBlack,              // gradient top
    surfaceVariant = DarkGray             // gradient bottom
)

private val LightColorScheme = lightColorScheme(
    primary = SkyBlue,                    // constant blue (today's stats)
    onBackground = NightBlack,            // main text
    onSurfaceVariant = GrayShade,         // location text
    surface = PureWhite,                  // card color
    background = SkyBlue,                 // gradient top
    surfaceVariant = PureWhite            // gradient bottom
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = AppTypography,
        content = content
    )
}