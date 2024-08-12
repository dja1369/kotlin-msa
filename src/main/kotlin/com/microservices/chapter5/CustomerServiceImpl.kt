package com.microservices.chapter5

import org.springframework.stereotype.Service
import reactor.core.publisher.Mono

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {
    override fun getCustomer(id: Int): Mono<Customer> = customerRepository.findById(id)
    override fun createCustomer(customer: Mono<Customer>): Mono<Customer> =
        customerRepository.create(customer)
}