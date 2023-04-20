package com.example.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class WeatherCode(
    @SerializedName("code")
    val code: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("icon")
    val icon: String?,
)