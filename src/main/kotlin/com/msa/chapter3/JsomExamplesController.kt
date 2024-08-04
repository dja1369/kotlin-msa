package com.msa.chapter3

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class JsomExamplesController {
    @GetMapping("/json")
//    fun getJson() = SimpleObject()
//    fun getJson() = SimpleObjectData()
    fun getJson() = ComplexObjectData(
        SimpleObjectData("more", "complex")
    )
}