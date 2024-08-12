package com.microservices.chapter5

import jakarta.annotation.PostConstruct
import org.springframework.data.mongodb.core.ReactiveMongoOperations
import org.springframework.stereotype.Component
import reactor.kotlin.core.publisher.toMono

@Component
class DatabaseInitializer(
    private val mongoOperations: ReactiveMongoOperations,
    private val customerRepository: CustomerRepository
) {
    companion object {
        private val initialCustomers = listOf(
            Customer(1, "kotlin"),
            Customer(2, "spring"),
            Customer(3, "microservices", Telephone("+44", "12345678"))
        )
    }
//    @PostConstruct // 클래스 초기화 시점에 실행
//    fun initData() {
//        mongoOperations.collectionExists("Customers").subscribe { exists ->
//            if(!exists){
//                mongoOperations.createCollection("Customers").subscribe {
//                    println("Customers collection created")
//                }
//            } else println("Customers collection already exists")
//            customerRepository.saveAll(initialCustomers).subscribe { // 데이터 저장시에 콜렉션이 없다면 생성해줌
//                println("Default customer created")
//            }
}