package com.example.weatherapp.modules

import com.example.weatherapp.api.WeatherAPI
import com.example.weatherapp.api.WeatherNetworkMapper
import com.example.weatherapp.repository.WeatherRepository
import com.example.weatherapp.repository.WeatherRepository_Impl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideWeatherRepository(
        weatherService: WeatherAPI,
        mapper: WeatherNetworkMapper
    ): WeatherRepository {
        return WeatherRepository_Impl(
            weatherService = weatherService,
            mapper = mapper
        )
    }
}