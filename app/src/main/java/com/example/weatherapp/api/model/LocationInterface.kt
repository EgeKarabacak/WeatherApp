package com.example.weatherapp.api.model

import android.location.Location

interface LocationInterface {
    suspend fun getLocation(): Location?
}