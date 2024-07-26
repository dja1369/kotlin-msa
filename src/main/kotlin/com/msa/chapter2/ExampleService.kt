package com.msa.chapter2

import org.springframework.beans.factory.annotation.Value

class ExampleService: ServiceInterface {
    @Value("\${service.message.text}")
    private lateinit var text: String

    override fun getHello(): String = "$text welcome!"
}