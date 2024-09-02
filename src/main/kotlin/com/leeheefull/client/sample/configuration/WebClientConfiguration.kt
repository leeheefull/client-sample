package com.leeheefull.client.sample.configuration

import io.netty.channel.ChannelOption
import io.netty.handler.timeout.ReadTimeoutHandler
import io.netty.handler.timeout.WriteTimeoutHandler
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.http.client.reactive.ReactorClientHttpConnector
import org.springframework.web.reactive.function.client.WebClient
import reactor.netty.http.client.HttpClient
import reactor.netty.resources.ConnectionProvider
import java.time.Duration

@Configuration
class WebClientConfiguration(
    @Value("\${client.max-connections}")
    private val maxConnections: Int,

    @Value("\${client.pending-acquire-timeout}")
    private val pendingAcquireTimeoutSeconds: Long,

    @Value("\${client.connection-timeout}")
    private val connectTimeoutMillis: Int,

    @Value("\${client.read-timeout}")
    private val readTimeoutSeconds: Int,

    @Value("\${client.write-timeout}")
    private val writeTimeoutSeconds: Int,
) {
    @Bean
    fun webClient(): WebClient {
        // Connection provider 설정
        val connectionProvider = ConnectionProvider.builder("customConnectionProvider")
            .maxConnections(maxConnections)    // 커넥션 풀 최대 연결 수(default : 무제한)
            .pendingAcquireTimeout(Duration.ofSeconds(pendingAcquireTimeoutSeconds))  // 커넥션을 얻기 위해 대기하는 최대 시간(default : 45s)
            .build()

        // HttpClient 설정
        val httpClient = HttpClient.create(connectionProvider)
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, connectTimeoutMillis)    // 커넥션 타임아웃 시간(default : 30000ms)
            .doOnConnected { connection ->
                connection.addHandlerLast(ReadTimeoutHandler(readTimeoutSeconds))   // 읽기 타임아웃 시간(default : 무제한)
                connection.addHandlerLast(WriteTimeoutHandler(writeTimeoutSeconds))  // 쓰기 타임아웃 시간(default : 무제한)
            }

        // WebClient 설정
        return WebClient.builder()
            .defaultHeader(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE)
            .clientConnector(ReactorClientHttpConnector(httpClient))
            .build()
    }
}