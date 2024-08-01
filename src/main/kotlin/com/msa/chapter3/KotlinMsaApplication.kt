package com.msa.chapter3

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import java.util.concurrent.ConcurrentHashMap

@SpringBootApplication
class KotlinMsaApplication {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "MicroService")
        )
    }

    @Bean
    fun customers() = ConcurrentHashMap<Int, Customer>(
//        initialCustomers.associateBy(Customer::id)
        // 둘은 어떤 차이가 있는가?
        initialCustomers.associateBy{ it.id }

    )
}

fun main(args: Array<String>) {
    runApplication<KotlinMsaApplication>(*args)
}
