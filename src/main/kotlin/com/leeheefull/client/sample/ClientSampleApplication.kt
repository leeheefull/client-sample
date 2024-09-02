package com.leeheefull.client.sample

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class ClientSampleApplication

fun main(args: Array<String>) {
    runApplication<ClientSampleApplication>(*args)
}
