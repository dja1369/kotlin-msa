package com.msa.chapter3

import org.springframework.stereotype.Component
import java.util.concurrent.ConcurrentHashMap

@Component
class CustomerServiceImpl : CustomerService {
    companion object{
        val initialCustomers = arrayOf(Customer(1, "Kotlin"),
                               Customer(2, "Spring"),
                               Customer(3, "Microservices")
        )
    }
    val customers = ConcurrentHashMap<Int, Customer>(initialCustomers.associateBy {it.id})

    override fun getCustomer(id: Int): Customer? = customers[id]

    override fun createCustomer(customer: Customer) {
        customers[customer.id] = customer
    }

    override fun updateCustomer(id: Int, customer: Customer) {
        deleteCustomer(id)
        createCustomer(customer)
    }

    override fun deleteCustomer(id: Int) {
        customers.remove(id)
    }

    override fun searchCustomer(nameFilter: String): List<Customer> {
        return customers.filter {
            it.value.name.contains(nameFilter, true)
        }.map { it.value }.toList()
    }
}