package com.example.weatherapp.api.model

import com.google.gson.annotations.SerializedName

data class WeatherDto(
    @SerializedName("data")
    val weatherData: List<Data>,
)