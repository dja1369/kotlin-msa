package com.msa.chapter4

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Component
class CustomerRouter {
    @Bean
    fun customerRoutes() = router {
        "/functional".nest {
            "/string".nest {
                GET("/") {
                    ok().bodyValue("Hello, Spring Webflux!")
                }
            }
            "/customer".nest {
                GET("/"){
                    ok().body(Mono.just(Customer(1, "Functional Customer")))
                }
            }
        }
    }
}