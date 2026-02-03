package com.example.weatherapp.data.location

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Geocoder
import android.location.Location
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.Priority
import com.google.android.gms.tasks.CancellationTokenSource
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import java.util.Locale
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

data class LocationData(
    val latitude: Double,
    val longitude: Double,
    val cityName: String?
)

sealed class LocationResult {
    data class Success(val location: LocationData) : LocationResult()
    data class Error(val message: String) : LocationResult()
    data object PermissionDenied : LocationResult()
}

@Singleton
class LocationService @Inject constructor(
    @ApplicationContext private val context: Context,
    private val fusedLocationClient: FusedLocationProviderClient
) {

    fun hasLocationPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED ||
        ContextCompat.checkSelfPermission(
            context,
            Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED
    }

    suspend fun getCurrentLocation(): LocationResult {
        if (!hasLocationPermission()) {
            return LocationResult.PermissionDenied
        }

        return try {
            val location = getLastLocation()
            if (location != null) {
                val cityName = getCityName(location.latitude, location.longitude)
                LocationResult.Success(
                    LocationData(
                        latitude = location.latitude,
                        longitude = location.longitude,
                        cityName = cityName
                    )
                )
            } else {
                LocationResult.Error("Unable to get location")
            }
        } catch (e: SecurityException) {
            LocationResult.PermissionDenied
        } catch (e: Exception) {
            LocationResult.Error(e.message ?: "Unknown error")
        }
    }

    @Suppress("MissingPermission")
    private suspend fun getLastLocation(): Location? = suspendCancellableCoroutine { continuation ->
        val cancellationTokenSource = CancellationTokenSource()

        fusedLocationClient.getCurrentLocation(
            Priority.PRIORITY_BALANCED_POWER_ACCURACY,
            cancellationTokenSource.token
        ).addOnSuccessListener { location ->
            continuation.resume(location)
        }.addOnFailureListener { exception ->
            continuation.resumeWithException(exception)
        }

        continuation.invokeOnCancellation {
            cancellationTokenSource.cancel()
        }
    }

    private fun getCityName(latitude: Double, longitude: Double): String? {
        return try {
            val geocoder = Geocoder(context, Locale.getDefault())
            @Suppress("DEPRECATION")
            val addresses = geocoder.getFromLocation(latitude, longitude, 1)
            addresses?.firstOrNull()?.locality ?: addresses?.firstOrNull()?.adminArea
        } catch (e: Exception) {
            null
        }
    }
}
