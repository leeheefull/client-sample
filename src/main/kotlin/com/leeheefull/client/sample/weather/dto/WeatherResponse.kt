package com.leeheefull.client.sample.weather.dto

import com.fasterxml.jackson.annotation.JsonProperty

data class WeatherResponse(
    val latitude: Double? = null,

    val longitude: Double? = null,

    @JsonProperty("generationtime_ms")
    val generationTimeMs: Double? = null,

    @JsonProperty("utc_offset_seconds")
    val utcOffsetSeconds: Int? = null,

    val timezone: String? = null,

    @JsonProperty("timezone_abbreviation")
    val timezoneAbbreviation: String? = null,

    val elevation: Double? = null,

    @JsonProperty("hourly_units")
    val hourlyUnits: HourlyUnits? = null,

    val hourly: Hourly? = null,
)

data class HourlyUnits(
    val time: String? = null,

    @JsonProperty("temperature_2m")
    val temperature2m: String? = null,
)

data class Hourly(
    val time: List<String>? = null,

    @JsonProperty("temperature_2m")
    val temperature2m: List<Double>? = null,
)
