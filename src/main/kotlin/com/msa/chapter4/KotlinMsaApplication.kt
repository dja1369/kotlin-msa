package com.msa.chapter4

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinMsaApplication

fun main(args: Array<String>) {
    runApplication<KotlinMsaApplication>(*args)
}