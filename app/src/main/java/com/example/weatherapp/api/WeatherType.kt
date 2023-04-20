package com.example.weatherapp.api

import androidx.annotation.DrawableRes
import com.example.weatherapp.R.drawable
//To select the correct icon for the weather type and the text to display about the day suggestions
sealed class WeatherType (
    val weatherDesc: String,
    @DrawableRes val iconRes: Int
) {
    object ClearSky : WeatherType(
        weatherDesc = "Clear sky",
        iconRes = drawable.sunny
    )
    object MainlyClear : WeatherType(
        weatherDesc = "Mainly clear",
        iconRes = drawable.mainly_clear
    )
    object PartlyCloudy : WeatherType(
        weatherDesc = "Partly cloudy",
        iconRes = drawable.partly_cloudy
    )
    object Overcast : WeatherType(
        weatherDesc = "Overcast",
        iconRes = drawable.overcast
    )
    object Foggy : WeatherType(
        weatherDesc = "Foggy",
        iconRes = drawable.overcast
    )
    object DepositingRimeFog : WeatherType(
        weatherDesc = "Depositing rime fog",
        iconRes = drawable.overcast
    )
    object LightDrizzle : WeatherType(
        weatherDesc = "Light drizzle",
        iconRes = drawable.lightrain
    )
    object ModerateDrizzle : WeatherType(
        weatherDesc = "Moderate drizzle",
        iconRes = drawable.lightrain
    )
    object DenseDrizzle : WeatherType(
        weatherDesc = "Dense drizzle",
        iconRes = drawable.rain
    )
    object LightFreezingDrizzle : WeatherType(
        weatherDesc = "Slight freezing drizzle",
        iconRes = drawable.freezing_rain
    )
    object DenseFreezingDrizzle : WeatherType(
        weatherDesc = "Dense freezing drizzle",
        iconRes = drawable.freezing_rain
    )
    object SlightRain : WeatherType(
        weatherDesc = "Slight rain",
        iconRes = drawable.lightrain
    )
    object ModerateRain : WeatherType(
        weatherDesc = "Rainy",
        iconRes = drawable.rain
    )
    object HeavyRain : WeatherType(
        weatherDesc = "Heavy rain",
        iconRes = drawable.heavyrain
    )
    object HeavyFreezingRain: WeatherType(
        weatherDesc = "Heavy freezing rain",
        iconRes = drawable.freezing_rain
    )
    object SlightSnowFall: WeatherType(
        weatherDesc = "Slight snow fall",
        iconRes = drawable.slight_snow
    )
    object ModerateSnowFall: WeatherType(
        weatherDesc = "Moderate snow fall",
        iconRes = drawable.slight_snow
    )
    object HeavySnowFall: WeatherType(
        weatherDesc = "Heavy snow fall",
        iconRes = drawable.snow
    )
    object SnowGrains: WeatherType(
        weatherDesc = "Snow grains",
        iconRes = drawable.snow
    )
    object SlightRainShowers: WeatherType(
        weatherDesc = "Slight rain showers",
        iconRes = drawable.rain
    )
    object ModerateRainShowers: WeatherType(
        weatherDesc = "Moderate rain showers",
        iconRes = drawable.heavyrain
    )
    object ViolentRainShowers: WeatherType(
        weatherDesc = "Violent rain showers",
        iconRes = drawable.heavyrain
    )
    object SlightSnowShowers: WeatherType(
        weatherDesc = "Light snow showers",
        iconRes = drawable.snow
    )
    object HeavySnowShowers: WeatherType(
        weatherDesc = "Heavy snow showers",
        iconRes = drawable.snow
    )
    object ModerateThunderstorm: WeatherType(
        weatherDesc = "Moderate thunderstorm",
        iconRes = drawable.thunderstrom
    )
    object SlightHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with slight hail",
        iconRes = drawable.thunderstrom
    )
    object HeavyHailThunderstorm: WeatherType(
        weatherDesc = "Thunderstorm with heavy hail",
        iconRes = drawable.thunderstrom
    )

    companion object {
        fun fromWMO(code: Int?): WeatherType {
            return when(code) {
                800 -> ClearSky
                801 -> MainlyClear
                802 -> PartlyCloudy
                803 -> PartlyCloudy
                804 -> Overcast
                741 -> Foggy
                751 -> DepositingRimeFog
                300 -> LightDrizzle
                301 -> ModerateDrizzle
                302 -> DenseDrizzle
                300 -> LightFreezingDrizzle
                302 -> DenseFreezingDrizzle
                500 -> SlightRain
                501 -> ModerateRain
                502 -> HeavyRain
                302 -> LightFreezingDrizzle
                511 -> HeavyFreezingRain
                600 -> SlightSnowFall
                601 -> ModerateSnowFall
                602 -> HeavySnowFall
                623 -> SnowGrains
                520 -> SlightRainShowers
                521 -> ModerateRainShowers
                522 -> ViolentRainShowers
                621 -> SlightSnowShowers
                622 -> HeavySnowShowers
                201 -> ModerateThunderstorm
                233 -> SlightHailThunderstorm
                233 -> HeavyHailThunderstorm
                else -> ClearSky
            }
        }
    }
}
