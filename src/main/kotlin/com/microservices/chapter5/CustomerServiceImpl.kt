package com.microservices.chapter5

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class CustomerServiceImpl(
    private val customerRepository: CustomerRepository
) : CustomerService {
    override fun getCustomer(id: Int): Mono<Customer> = customerRepository.findById(id)
    override fun createCustomer(customer: Mono<Customer>): Mono<Customer> =
        customerRepository.create(customer)

    override fun deleteCustomer(id: Int): Mono<Boolean> =
        customerRepository.deleteById(id).map { it.deletedCount > 0 } // 삭제된 데이터의 개수가 0보다 크면 true 반환

    override fun searchCustomers(nameFilter: String): Flux<Customer> =
        customerRepository.findCustomer(nameFilter)
}