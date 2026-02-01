package com.example.weatherapp.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(

    // Full screen background (behind everything)
    background = NightBlack,
    onBackground = PureWhite,

    // Main surfaces (text sits on this)
    surface = NightBlack,

    // Primary content text (main temperature, section titles)
    onSurface = PureWhite,

    // Secondary / muted surfaces (chips, cards if reused)
    surfaceVariant = NightBlack,

    // Secondary / muted text (location, labels, metadata)
    onSurfaceVariant = GrayShade,

    // Accent color (icons, highlights, weather symbols)
    primary = SkyBlue,
    onPrimary = NightBlack
)

private val LightColorScheme = lightColorScheme(

    // Full screen background (top sky area)
    background = SkyBlue,
    onBackground = NightBlack,

    // Main surfaces (cards, lists)
    surface = PureWhite,

    // Primary content text (main temperature, section titles)
    onSurface = NightBlack,

    // Secondary / muted surfaces (chips, cards if reused)
    surfaceVariant = PureWhite,

    // Secondary / muted text (location, labels, metadata)
    onSurfaceVariant = GrayShade,

    // Accent color (icons, highlights, weather symbols)
    primary = SkyBlue,
    onPrimary = NightBlack
)



@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = AppTypography,
        content = content
    )
}