package com.msa.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.ConcurrentHashMap

@RestController
class CustomerController {
    @Autowired
    lateinit var customers: ConcurrentHashMap<Int, Customer>

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int) = customers[id]

    @GetMapping("/customers")
    fun getCustomers() = customers.map {
        it.value
    }.toList()
}