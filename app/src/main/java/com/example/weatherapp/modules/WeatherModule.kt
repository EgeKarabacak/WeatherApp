package com.example.weatherapp.modules

import com.example.weatherapp.api.WeatherAPI
import com.example.weatherapp.api.WeatherNetworkMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object WeatherModule {

    @Provides
    @Singleton
    fun provideWeatherNetworkMapper(): WeatherNetworkMapper {
        return WeatherNetworkMapper()
    }


    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherbit.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherAPI::class.java)
    }

}