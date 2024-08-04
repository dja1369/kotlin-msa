package com.msa.chapter3

class SimpleObject {
    val name = "hello"
    private val zone = "world"
    fun getPlace() = zone // 스프링에서 자동으로 get 이 프리픽스된 함수를 호출해 직렬화 시킴
}

data class SimpleObjectData(val name: String = "hello", val place: String = "world") // 직렬화를 위한 데이터 클래스

data class ComplexObjectData(var object1: SimpleObjectData? = null)