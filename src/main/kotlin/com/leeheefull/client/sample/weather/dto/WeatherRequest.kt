package com.leeheefull.client.sample.weather.dto

data class WeatherRequest(
    val latitude: Double,
    val longitude: Double,
    val hourly: String,
)