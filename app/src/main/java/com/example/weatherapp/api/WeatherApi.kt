package com.example.weatherapp.api

import com.example.weatherapp.api.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/v2.0/current")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String = "170fae50429c4b75920e4a5c02e09a2f",
    ): WeatherDto
    @GET("/v2.0/current")
    suspend fun getWeatherCity(
        @Query("city") city: String,
        @Query("key") apiKey: String = "170fae50429c4b75920e4a5c02e09a2f",
    ): WeatherDto
}