package com.example.weatherapp.presentation.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.model.LocationInterface
import com.example.weatherapp.domain.model.Weather
import com.example.weatherapp.repository.WeatherRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: WeatherRepository,
    private val locationTracker: LocationInterface,
): ViewModel() {
    init {
        println("Repository: $repository")
    }
    private val _weather = MutableLiveData<Weather>()
    val weather: LiveData<Weather> get() = _weather


    fun fetchWeather() {
        viewModelScope.launch {
            val location = locationTracker.getLocation()
            val weather = location?.let { repository.getWeather(it.latitude, location.longitude) }
            println("lat: ${location?.latitude}")
            println("long: ${location?.longitude}")
            println("Weather: $weather")
            // Update the LiveData with the fetched weather data
            _weather.postValue(weather)
        }
    }
    fun fetchWeatherCity(city: String) {
        viewModelScope.launch {
            val weather =  repository.getWeatherByCity(city)
            println("Weather: $weather")
            // Update the LiveData with the fetched weather data
            _weather.postValue(weather)
        }
    }
}