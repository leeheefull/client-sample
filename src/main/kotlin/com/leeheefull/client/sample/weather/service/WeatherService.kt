package com.leeheefull.client.sample.weather.service

import com.leeheefull.client.sample.weather.client.WeatherClient
import com.leeheefull.client.sample.weather.dto.WeatherResponse
import org.springframework.stereotype.Service

@Service
class WeatherService(
    private val weatherClient: WeatherClient,
) {
    fun getWeatherData(): WeatherResponse {
        return weatherClient.getWeatherData()
    }
}