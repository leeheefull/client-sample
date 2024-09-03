package com.leeheefull.client.sample.weather.service

import com.leeheefull.client.sample.weather.client.WeatherClient
import com.leeheefull.client.sample.weather.dto.WeatherRequest
import com.leeheefull.client.sample.weather.dto.WeatherResponse
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class WeatherServiceTest : StringSpec({
    val weatherClient = mockk<WeatherClient>()
    val weatherService = WeatherService(weatherClient)

    "성공" {
        // given
        val weatherRequest = WeatherRequest(latitude = 37.5665, longitude = 126.9780, hourly = "temperature_2m")
        val clientResponse = WeatherResponse(latitude = 37.5665)

        // mocking
        every { weatherClient.getWeatherData(weatherRequest) } returns clientResponse

        // when
        val result = weatherService.getWeatherData()

        // then
        result shouldBe clientResponse
        verify(exactly = 1) { weatherClient.getWeatherData(weatherRequest) }
    }
})