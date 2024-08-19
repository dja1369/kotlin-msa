package com.microservices.chapter5

import jakarta.annotation.PostConstruct
import org.springframework.data.mongodb.core.ReactiveMongoTemplate
import org.springframework.data.mongodb.core.find
import org.springframework.data.mongodb.core.findById
import org.springframework.data.mongodb.core.query.Criteria.where
import org.springframework.data.mongodb.core.query.Query
import org.springframework.data.mongodb.core.query.isEqualTo
import org.springframework.data.mongodb.core.remove
import org.springframework.stereotype.Repository
import reactor.core.publisher.Mono
import reactor.kotlin.core.publisher.toMono

//interface CustomerRepository: ReactiveCrudRepository<Customer, Int> {
//}
@Repository
class CustomerRepository(private val template: ReactiveMongoTemplate) {
    companion object {
        private val initialCustomers = listOf(
            Customer(1, "kotlin"),
            Customer(2, "spring"),
            Customer(3, "microservices", Telephone("+44", "12345678"))
        )
    }
    @PostConstruct // 클래스 초기화 시점에 실행
    fun initializeRepository() =
        initialCustomers.map { it.toMono() }
            .map { create(it).subscribe() }

    fun findById(id: Int) = template.findById<Customer>(id)

    fun findCustomer(nameFilter: String) =
        template.find<Customer>(Query(where("name").regex(".*$nameFilter.*", "i"))) // i 옵션은 대소문자 구분하지 않음

    fun create(customer: Mono<Customer>) = template.save(customer)

    fun deleteById(id: Int) =
        template.remove<Customer>(Query(where("_id").isEqualTo(id)))
}