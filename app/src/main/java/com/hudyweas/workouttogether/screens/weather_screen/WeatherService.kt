package com.hudyweas.workouttogether.screens.weather_screen


import com.google.firebase.crashlytics.internal.network.HttpResponse
import com.google.gson.Gson
import com.hudyweas.workouttogether.api.ForecastResponse
import com.hudyweas.workouttogether.api.WeatherResponse
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class WeatherService @Inject constructor(private val httpClient: HttpClient) {
    // https://www.weatherapi.com/my/
    companion object {
        const val API_KEY: String = "197b71b4469143638e770018241904"
    }
    suspend fun getWeather(location: String): String = withContext(Dispatchers.IO) {
        val response: HttpResponse = httpClient.get("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/Poznan/2024-04-22/2024-04-22?unitGroup=metric&include=days&key=ETLHFFUVFZGYUNGTMZJDUR4EG&contentType=json")
        val responseBody = response.body()
        println(responseBody)
        return@withContext responseBody
    }

    suspend fun sendGet(city:String, date:String): ForecastResponse = withContext(Dispatchers.IO) {
        val API_KEY = "ETLHFFUVFZGYUNGTMZJDUR4EG"

        //convert date variable to format yyyy-MM-dd
        val formattedDate = convertDateString(date)
        val cityEn = replacePolishChars(city)
        val responseString = httpClient.get<String>("https://weather.visualcrossing.com/VisualCrossingWebServices/rest/services/timeline/$cityEn/$formattedDate/$formattedDate?unitGroup=metric&include=days&key=$API_KEY&contentType=json")
        println(responseString)
        // use Gson
        val gson = Gson()
        val forecastResponse = gson.fromJson(responseString, ForecastResponse::class.java)
        println(forecastResponse)
        return@withContext forecastResponse
    }
}

fun replacePolishChars(city: String): String {
    return city.replace("ą", "a").replace("ć", "c").replace("ę", "e").replace("ł", "l").replace("ń", "n").replace("ó", "o").replace("ś", "s").replace("ż", "z").replace("ź", "z")
}

fun convertDateString(inputDate: String): String {
    val inputFormat = SimpleDateFormat("EEE, dd MMM yyyy", Locale.ENGLISH)
    val outputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

    val date: Date? = inputFormat.parse(inputDate)
    return if (date != null) {
        outputFormat.format(date)
    } else {
        ""
    }
}