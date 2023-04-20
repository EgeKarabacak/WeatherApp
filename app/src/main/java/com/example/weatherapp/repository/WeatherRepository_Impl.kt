package com.example.weatherapp.repository

import com.example.weatherapp.api.WeatherAPI
import com.example.weatherapp.api.WeatherNetworkMapper
import com.example.weatherapp.domain.model.Weather
import javax.inject.Inject

class WeatherRepository_Impl @Inject constructor(
    private val weatherService: WeatherAPI,
    private val mapper : WeatherNetworkMapper

) : WeatherRepository {

    override suspend fun getWeather(latitude: Double, longitude: Double): Weather {
        return mapper.mapToDomainModel(weatherService.getWeather(latitude, longitude))
    }

    override suspend fun getWeatherByCity(city: String): Weather {
        return mapper.mapToDomainModel(weatherService.getWeatherCity(city))
    }
}