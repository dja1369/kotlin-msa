package com.msa.chapter4

import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.body
import org.springframework.web.reactive.function.server.router
import reactor.core.publisher.Mono

@Component
class CustomerRouter(
    private val customerHandler: CustomerHandler
) {
    @Bean
    fun customerRoutes() = router {
        "/functional".nest {
            "/string".nest {
                GET("/") {
                    ok().bodyValue("Hello, Spring Webflux!")
                }
            }
            "/customer".nest {
                GET("/{id}", customerHandler::get) // -> 라우터 + 핸들러(클래스) 메소드 참조
//                {
//                    ok().body(Mono.just(Customer(1, "Functional Customer"))) // -> 라우터 + 로직
//                    it: ServerRequest -> customerHandler.get(it) // -> 라우터 + 람다 + 핸들러(클래스)
//                }
                POST("/", customerHandler::create)
            }
            "customers".nest {
                GET("/", customerHandler::search)
            }
        }
    }
}