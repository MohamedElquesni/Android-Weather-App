# Weather App

Android weather app built with Jetpack Compose. Fetches live data from [Open-Meteo](https://open-meteo.com/) and supports GPS location, dark mode, and Arabic (RTL).

## Screenshots

<table>
  <tr>
    <td><img src="Screenshots/Light%20Mode%20Extended.png"></td>
    <td><img src="Screenshots/Dark%20Mode%20Extended.png"></td>
    <td><img src="Screenshots/Ligh%20Mode%20Extended%20Arabic.png"></td>
    <td><img src="Screenshots/Dark%20Mode%20Extended%20Arabic.png"></td>
  </tr>
  <tr>
    <td><img src="Screenshots/Ligh%20Mode%20Collapsed.png"></td>
    <td><img src="Screenshots/Dark%20Mode%20Collapsed.png"></td>
    <td><img src="Screenshots/Light%20Mode%20Collapsed%20Arabic.png"></td>
    <td><img src="Screenshots/Dark%20Mode%20Collapsed%20Arabic.png"></td>
  </tr>
  <tr>
    <td colspan="4" align="center"><img src="Screenshots/Allow%20Location%20Dialogue.png" width="25%"></td>
  </tr>
</table>

## Features

- Current conditions: temperature, feels like, wind, humidity, UV index, pressure
- Hourly forecast with weather icons
- 7-day forecast
- Collapsing header on scroll
- GPS location with city name
- Arabic language support (RTL)
- Light and dark theme

## Tech Stack

- Jetpack Compose + Material 3
- MVVM + Hilt
- Retrofit + Gson ([Open-Meteo API](https://open-meteo.com/))
- FusedLocationProvider

## Requirements

- Android 7.0+ (API 24)
- Location permission (optional — falls back to current location label)
