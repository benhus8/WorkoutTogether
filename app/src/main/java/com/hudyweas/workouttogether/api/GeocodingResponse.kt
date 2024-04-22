package com.hudyweas.workouttogether.api

data class DataSource(
  val sourceName: String,
  val attribution: String,
  val license: String,
  val url: String
)

data class Timezone(
  val name: String,
  val offsetSTD: String,
  val offsetSTDSeconds: Int,
  val offsetDST: String,
  val offsetDSTSeconds: Int,
  val abbreviationSTD: String,
  val abbreviationDST: String
)

data class Rank(
  val importance: Double,
  val popularity: Double,
  val confidence: Int,
  val confidenceCityLevel: Int,
  val confidenceStreetLevel: Int,
  val matchType: String
)

data class BoundingBox(
  val lon1: Double,
  val lat1: Double,
  val lon2: Double,
  val lat2: Double
)

data class Result(
  val datasource: DataSource,
  val country: String,
  val countryCode: String,
  val state: String,
  val city: String,
  val postcode: String,
  val district: String,
  val suburb: String,
  val street: String,
  val houseNumber: String,
  val lon: Double,
  val lat: Double,
  val resultType: String,
  val formatted: String,
  val addressLine1: String,
  val addressLine2: String,
  val category: String,
  val timezone: Timezone,
  val plusCode: String,
  val rank: Rank,
  val placeId: String,
  val bbox: BoundingBox
)

data class Query(
  val text: String,
  val houseNumber: String,
  val street: String,
  val city: String,
  val country: String,
  val parsed: Parsed
)

data class Parsed(
  val houseNumber: String,
  val street: String,
  val city: String,
  val country: String,
  val expectedType: String
)

data class ApiResponse(
  val results: List<Result>,
  val query: Query
)