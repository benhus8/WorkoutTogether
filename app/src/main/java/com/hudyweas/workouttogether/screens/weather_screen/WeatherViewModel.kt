package com.hudyweas.workouttogether.screens.weather_screen

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.hudyweas.workouttogether.api.ForecastResponse
import com.hudyweas.workouttogether.api.WeatherResponse
import com.hudyweas.workouttogether.model.service.LogService
import com.hudyweas.workouttogether.screens.WorkoutTogetherViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    logService: LogService,
    private val weatherService: WeatherService
) : WorkoutTogetherViewModel(logService) {
    private val _weather = mutableStateOf<ForecastResponse?>(null)
    val weather: State<ForecastResponse?> get() = _weather

    fun getWeather() = viewModelScope.launch {
        _weather.value = weatherService.sendGet("London", "2022-12-20")
    }

}