package com.example.weatherapp.api

import com.example.weatherapp.api.model.WeatherCode
import com.example.weatherapp.api.model.WeatherDto
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.domain.util.DomainMapper
import com.example.weatherapp.api.model.Data

class WeatherNetworkMapper : DomainMapper<WeatherDto, Weather> {

    override fun mapToDomainModel(model: WeatherDto): Weather {
        val firstData = model.weatherData.firstOrNull()
        return if (firstData != null) {
            Weather(
                city_name = firstData.cityName,
                app_temp = firstData.appTemp,
                rh = firstData.rh,
                temp = firstData.temp,
                uv = firstData.uv,
                wind_spd = firstData.windSpd,
                code = firstData.weatherCode.code,
                description = firstData.weatherCode.description,
            )
        } else {
            // Return a default Weather object or throw an exception
            Weather(
                app_temp = null,
                rh = null,
                temp = null,
                uv = null,
                wind_spd = null,
                code = -1,
            )
        }
    }
    //We need this to switch between cities and store them in repo
    //Search function is broken so this doesn't get utilized right now
    override fun mapFromDomainModel(domainModel: Weather): WeatherDto {
        return WeatherDto(
            weatherData = listOf(
                Data(
                    cityName = domainModel.city_name,
                    appTemp = domainModel.app_temp,
                    rh = domainModel.rh,
                    temp = domainModel.temp,
                    uv = domainModel.uv,
                    windSpd = domainModel.wind_spd,
                    weatherCode = WeatherCode(
                        code = domainModel.code,
                        description = null,
                        icon = null,
                    )
                )
            )
        )
    }
}