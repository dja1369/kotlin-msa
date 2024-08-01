package com.msa.chapter3

interface CustomerService {
    fun getCustomer(): Customer?
    fun createCustomer(customer: Customer)
    fun updateCustomer(id: Int, customer: Customer)
    fun deleteCustomer(id: Int)
    fun searchCustomer(nameFilter: String): List<Customer>
}