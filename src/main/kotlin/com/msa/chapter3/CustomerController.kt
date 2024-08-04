package com.msa.chapter3

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class CustomerController {
    @Autowired
    lateinit var customerService: CustomerService

    @GetMapping("/customer/{id}")
    fun getCustomer(@PathVariable id: Int): ResponseEntity<Customer?> {
        val customer = customerService.getCustomer(id)
        val status = customer?.let { HttpStatus.OK } ?: HttpStatus.NOT_FOUND
        return ResponseEntity(customer, status)
    }

    @PostMapping("/customer")
    fun createCustomer(@RequestBody customer: Customer): ResponseEntity<Unit?> {
        customerService.createCustomer(customer)
        return ResponseEntity(null, HttpStatus.CREATED)
    }

    @DeleteMapping("/customer/{id}")
    fun deleteCustomer(@PathVariable id: Int): ResponseEntity<Unit> {
        return when (customerService.getCustomer(id)) {
            null -> ResponseEntity(Unit, HttpStatus.NOT_FOUND)
            else -> {
                customerService.deleteCustomer(id)
                ResponseEntity(Unit, HttpStatus.OK)
            }
        }
    }

        @PutMapping("/customer/{id}")
        fun updateCustomer(@PathVariable id: Int, @RequestBody customer: Customer): ResponseEntity<Unit> {
            return when (customerService.getCustomer(id)) {
                null -> ResponseEntity(Unit, HttpStatus.NOT_FOUND)
                else -> {
                    customerService.updateCustomer(id, customer)
                    ResponseEntity(Unit, HttpStatus.ACCEPTED)
                }
            }
        }

        @GetMapping("/customers")
        fun getCustomers(@RequestParam(required = false, defaultValue = "") nameFilter: String) =
            customerService.searchCustomer(nameFilter)
    }
