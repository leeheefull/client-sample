package com.leeheefull.client.sample.weather.service

import com.leeheefull.client.sample.weather.client.WeatherClient
import com.leeheefull.client.sample.weather.dto.WeatherRequest
import com.leeheefull.client.sample.weather.dto.WeatherResponse
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class WeatherService(
    private val weatherClient: WeatherClient,
) {
    private val weatherCache: ConcurrentHashMap<WeatherRequest, WeatherResponse> = ConcurrentHashMap()

    @Scheduled(fixedRate = 10000)
    fun clearCache() {
        weatherCache.clear()
    }

    fun getWeatherData(): WeatherResponse {
        val weatherRequest = WeatherRequest(
            latitude = 37.5665,
            longitude = 126.9780,
            hourly = "temperature_2m",
        )

        return weatherCache[weatherRequest] ?: run {
            weatherClient.getWeatherData(weatherRequest)
                .also { weatherCache[weatherRequest] = it }
        }
    }
}