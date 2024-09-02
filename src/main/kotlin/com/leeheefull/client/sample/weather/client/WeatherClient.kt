package com.leeheefull.client.sample.weather.client

import com.leeheefull.client.sample.weather.dto.WeatherResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient

@Component
class WeatherClient(
    private val webClient: WebClient,

    @Value("\${client.weather.scheme}")
    private val uriScheme: String,

    @Value("\${client.weather.host}")
    private val uriHost: String,
) {
    fun getWeatherData(): WeatherResponse {
        return webClient.get()
            .uri { uriBuilder ->
                uriBuilder
                    .scheme(uriScheme)
                    .host(uriHost)
                    .path("/v1/forecast")
                    .queryParam("latitude", "37.5665")
                    .queryParam("longitude", "126.9780")
                    .queryParam("hourly", "temperature_2m")
                    .build()
            }
            .retrieve()
            .bodyToMono(WeatherResponse::class.java)
            .block() ?: throw RuntimeException("날씨 데이터 응답 null")
    }
}