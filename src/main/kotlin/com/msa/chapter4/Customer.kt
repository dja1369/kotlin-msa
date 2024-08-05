package com.msa.chapter4

data class Customer(
    var id: Int,
    val name: String,
    var telephone: Telephone? = null
)
data class Telephone (
    var countryCode: String = "",
    var telephoneNumber: String = ""
)
