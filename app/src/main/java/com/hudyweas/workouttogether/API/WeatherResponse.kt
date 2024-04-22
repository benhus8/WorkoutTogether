package com.hudyweas.workouttogether.API

import kotlinx.serialization.Serializable


data class WeatherResponse(
  val location: Location,
  val current: Current
)
