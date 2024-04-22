package com.hudyweas.workouttogether.screens.weather_screen


import com.google.gson.Gson
import com.hudyweas.workouttogether.api.WeatherResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class WeatherService @Inject constructor(private val httpClient: HttpClient) {
    // https://www.weatherapi.com/my/
    companion object {
        const val API_KEY: String = "197b71b4469143638e770018241904"
    }
    suspend fun getWeather(location: String): WeatherResponse = withContext(Dispatchers.IO) {
        val response: WeatherResponse = httpClient.get("https://api.weatherapi.com/v1/current.json?key=197b71b4469143638e770018241904&q=London&aqi=yes")
        return@withContext response
    }

    suspend fun sendGet(): WeatherResponse = withContext(Dispatchers.IO) {

        val responseString = httpClient.get<String>("https://api.weatherapi.com/v1/current.json?key=197b71b4469143638e770018241904&q=London&aqi=yes")
        println(responseString)
        // use Gson
        val gson = Gson()
        val weatherResponse = gson.fromJson(responseString, WeatherResponse::class.java)
        println(weatherResponse.location?.name)
        println(weatherResponse.current?.temp_c)
        return@withContext weatherResponse
    }
}