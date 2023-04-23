package com.example.weatherapp.api

import com.example.weatherapp.api.model.WeatherDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {
    @GET("/v2.0/current")
    suspend fun getWeather(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("key") apiKey: String = "Your Api Key Here",
    ): WeatherDto
    @GET("/v2.0/current")
    suspend fun getWeatherCity(
        @Query("city") city: String,
        @Query("key") apiKey: String = "Your Api Key Here",
    ): WeatherDto
}
