package com.example.weatherapp.repository

import com.example.weatherapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(latitude: Double, longitude: Double): Weather?
    suspend fun getWeatherByCity(city: String): Weather?
}