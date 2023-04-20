package com.example.weatherapp.repository

import android.app.Application
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.example.weatherapp.api.model.LocationInterface
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume

class DefaultLocation @Inject constructor(
    private val locationClient: FusedLocationProviderClient,
    private val application: Application
) : LocationInterface {

    override suspend fun getLocation(): Location? {
        //check if we have fine location permission
        val accessFineLocation = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_FINE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        //check if we have coarse location permission
        val accessCourseLocation = ContextCompat.checkSelfPermission(
            application,
            android.Manifest.permission.ACCESS_COARSE_LOCATION
        ) == PackageManager.PERMISSION_GRANTED

        //import location manager and check if gps or network is enabled.
        val locationManager = application.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gpsEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.GPS_PROVIDER)

        //if we don't have access to anything we return null
        if(!gpsEnabled || !accessCourseLocation || !accessFineLocation){
            return null
        }

        //if we have access to location we return the last known location
        return suspendCancellableCoroutine { cont ->
            locationClient.lastLocation.apply {
                if (isComplete){
                    if(isSuccessful)
                        cont.resume(result)
                    else
                        cont.resume(null)
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    cont.resume(it)
                }
                addOnFailureListener {
                    cont.resume(null)
                }
                addOnCanceledListener {
                    cont.cancel()
                }
            }
        }
    }
}
