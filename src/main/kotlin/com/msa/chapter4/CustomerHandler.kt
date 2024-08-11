package com.msa.chapter4

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.*
import reactor.core.publisher.Mono
import java.net.URI

@Component
class CustomerHandler(
    private val customerService: CustomerService
) {
    /**
     * 단일객체 반환시 bodyValue 사용
     * 리액티브 객체 반환시 body 사용 -> body(비동기 적으로 데이터를 전송)
     */
    fun get(serverRequest: ServerRequest): Mono<ServerResponse> {
//        return ok().bodyValue(Mono.just(Customer(serverRequest.pathVariable("id").toInt(), "Functional Customer")))
//        return ok().body(
//                customerService.getCustomer(
//                    serverRequest.pathVariable("id").toInt()), Customer::class.java)
        return customerService.getCustomer(serverRequest.pathVariable("id").toInt())
            .flatMap { ok().bodyValue(it) }
            .switchIfEmpty(notFound().build())
        // 모노 객체 구독 -> 값이 있으면 it 에서 객체 반환 -> bodyValue 로 반환
    }
    fun search(serverRequest: ServerRequest) =
//        ok().body(customerService.searchCustomers(""), Customer::class.java)
        ok().body(customerService.searchCustomers(serverRequest.queryParam("nameFilter").orElseGet {""} ), Customer::class.java)

    fun create(serverRequest: ServerRequest) =
        customerService.createCustomer(serverRequest.bodyToMono(Customer::class.java)).flatMap {
            created(URI.create("/functional/customer/${it.id}")).build()
        }.onErrorResume(Exception::class.java) {
            badRequest().bodyValue(ErrorResponse("Error creating customer", it.message ?: "error"))
        }

}