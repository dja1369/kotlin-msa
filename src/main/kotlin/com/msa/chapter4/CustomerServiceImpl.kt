package com.msa.chapter4

import org.springframework.stereotype.Component
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import java.util.concurrent.ConcurrentHashMap

@Component class CustomerServiceImpl : CustomerService {
    companion object {
        val initialCustomers = arrayOf(
            Customer(1, "Kotlin"),
            Customer(2, "Spring"),
            Customer(3, "Microservice", Telephone("+44", "7123456789"))
        )
    }
    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy { it.id })

    override fun getCustomer(id: Int): Mono<Customer> {
        return customers[id]?.let { Mono.just(it) } ?: Mono.empty()
    }

    override fun searchCustomers(nameFilter: String): Flux<Customer> {
        return Flux.fromIterable(
        customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map(Map.Entry<Int, Customer>::value)
        )
    }

    override fun createCustomer(customerMono: Mono<Customer>): Mono<*> {
        return customerMono.flatMap {
            customers[it.id] = it
            Mono.just(it)
//            Mono.empty<Customer>()
        }
    }
}