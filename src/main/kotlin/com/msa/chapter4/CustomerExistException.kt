package com.msa.chapter4

data class CustomerExistException(override val message: String) : Exception(message)
