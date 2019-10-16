package com.eugeneek.wheatherio.feature.weather.data

import android.content.Context
import com.google.android.gms.location.LocationServices


class LocationProvider(context: Context) {

    private val locationClient = LocationServices.getFusedLocationProviderClient(context)

//    fun isPermissionGranted(): Boolean {
//
//
//
//    }
}