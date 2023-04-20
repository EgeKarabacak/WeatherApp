package com.example.weatherapp.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Weather(
    val temp: Double? = null,
    val uv: Double? = null,
    val wind_spd: Double? = null,
    val percip: Int? = null,
    val rh: Int? = null,
    val app_temp : Double? = null,
    val code : Int? = null,
    val description : String? = null,
    val city_name : String? = null,
) : Parcelable