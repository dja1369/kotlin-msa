package com.microservices.configclient

import org.springframework.beans.factory.annotation.Value
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class GreetingsController {
    @Value("\${microservice.example.greetings}")
    private lateinit var greetings: String

    @GetMapping("/greetings")
    fun greetings(): String {
        return greetings
    }
    fun todo() {
        TODO(" 콘피그 클라이언트에 등록한 앱 이름에 맞게 콘피그 서버에 yml 파일 추가하기")
    }
}