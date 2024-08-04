package com.msa.chapter3

class SimpleObject {
    val name = "hello"
    private val place = "world"
    fun getPlace() = place // 스프링에서 자동으로 get 이 프리픽스된 함수를 호출해 직렬화 시킴
}