package com.example.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("city_name")
    val cityName: String?,
    @SerializedName("app_temp")
    val appTemp: Double?,
    @SerializedName("rh")
    val rh: Int?,
    @SerializedName("temp")
    val temp: Double?,
    @SerializedName("uv")
    val uv: Double?,
    @SerializedName("weather")
    val weatherCode: WeatherCode,
    @SerializedName("wind_spd")
    val windSpd: Double?,
)