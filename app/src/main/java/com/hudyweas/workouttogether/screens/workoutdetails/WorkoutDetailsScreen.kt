package com.hudyweas.workouttogether.screens.workoutdetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.compose.AppTheme
import com.hudyweas.workouttogether.R
import com.hudyweas.workouttogether.api.ForecastResponse
import com.hudyweas.workouttogether.common.composable.ActionToolbar
import com.hudyweas.workouttogether.common.composable.RegularCardEditor
import com.hudyweas.workouttogether.common.ext.card
import com.hudyweas.workouttogether.common.ext.spacer
import com.hudyweas.workouttogether.common.ext.toolbarActions
import com.hudyweas.workouttogether.model.Workout

@Composable
fun WorkoutDetailsScreen(
    popUpScreen: () -> Unit,
    viewModel: WorkoutDetailsViewModel = hiltViewModel()
) {
    val workout by viewModel.workout
    val weatherResponse by viewModel.weatherResponse

    WorkoutDetailsScreenContent(
        workout = workout,
        weatherResponse = weatherResponse
    )
}

@Composable
fun WorkoutDetailsScreenContent(
    modifier: Modifier = Modifier,
    workout: Workout,
    weatherResponse: ForecastResponse
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(MaterialTheme.colorScheme.background)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ActionToolbar(
            title = R.string.workout_details,
            modifier = Modifier.toolbarActions(),
        )

        Spacer(modifier = Modifier.spacer())

        RegularCardEditor(R.string.title, R.drawable.ic_title, workout.title, Modifier.card(), {})
        RegularCardEditor(
            R.string.description,
            R.drawable.ic_description,
            workout.description,
            Modifier.card(),
            {})
        RegularCardEditor(R.string.date, R.drawable.ic_calendar, workout.date, Modifier.card(), {})
        RegularCardEditor(R.string.time, R.drawable.ic_clock, workout.time, Modifier.card(), {})

        Spacer(modifier = Modifier.spacer())


        weatherResponse.days?.firstOrNull()?.let { day ->
            Text(text = "Workout day weather forecast", style = MaterialTheme.typography.titleLarge, modifier = Modifier.padding(16.dp).align(Alignment.Start))
            RegularCardEditor(
                R.string.temp,
                R.drawable.temperature_celsius,
                "${day.temp} °C",
                Modifier.card(),
                {}
            )
            RegularCardEditor(
                R.string.feels_like,
                R.drawable.account,
                "${day.feelslike} °C",
                Modifier.card(),
                {}
            )
            RegularCardEditor(
                R.string.conditions,
                R.drawable.weather_cloudy_arrow_right,
                day.conditions,
                Modifier.card(),
                {}
            )
            RegularCardEditor(
                R.string.wind_speed,
                R.drawable.weather_windy,
                "${day.windspeed} m/s",
                Modifier.card(),
                {}
            )
            RegularCardEditor(
                R.string.sunrise,
                R.drawable.weather_sunset_up,
                "${day.sunrise}",
                Modifier.card(),
                {}
            )
            RegularCardEditor(
                R.string.sunset,
                R.drawable.weather_sunset_down,
                "${day.sunset}",
                Modifier.card(),
                {}
            )
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreviewWorkoutDetailsScreen() {
    val workout = Workout(
        title = "Workout title",
        description = "Workout description",
        date = "Mon, 1 Jan 2022",
        time = "12:00",
    )

    val weatherResponse = ForecastResponse(
        latitude = 0.0,
        longitude = 0.0,
        resolvedAddress = "Warsaw",
        address = "Warsaw",
        timezone = "Europe/Warsaw",
        tzoffset = 0,
        days = listOf(),
        stations = null,
        queryCost = null
    )

    AppTheme {
        WorkoutDetailsScreenContent(
            workout = workout,
            weatherResponse = weatherResponse
        )
    }
}