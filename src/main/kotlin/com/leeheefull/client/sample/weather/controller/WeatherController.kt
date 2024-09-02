package com.leeheefull.client.sample.weather.controller

import com.leeheefull.client.sample.weather.dto.WeatherResponse
import com.leeheefull.client.sample.weather.service.WeatherService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/weather")
class WeatherController(
    private val weatherService: WeatherService,
) {
    @GetMapping
    fun getWeatherData(): WeatherResponse {
        return weatherService.getWeatherData()
    }
}