package com.microservices.discoveryserver

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DiscoveryserverApplication

fun main(args: Array<String>) {
    runApplication<DiscoveryserverApplication>(*args)
}
