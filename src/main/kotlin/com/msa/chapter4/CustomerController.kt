package com.msa.chapter4

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@RestController
class CustomerController(
    private val customerService: CustomerService
) {
    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Mono<Customer>> {
        val customer = customerService.getCustomer(id)
        return ResponseEntity(customer, HttpStatus.OK)
    }
    @GetMapping("/customers")
    fun searchCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String): ResponseEntity<Flux<Customer>> {
        val customers = customerService.searchCustomers(nameFilter)
        return ResponseEntity(customers, HttpStatus.OK)
    }
    @PostMapping("/customer")
    fun createCustomer(@RequestBody customerMono: Mono<Customer>) =
        ResponseEntity(customerService.createCustomer(customerMono), HttpStatus.CREATED)
}