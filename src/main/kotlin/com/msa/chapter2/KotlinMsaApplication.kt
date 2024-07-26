package com.msa.chapter2

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@SpringBootApplication
class KotlinMsaApplication{

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}' == 'simple'}")
    fun exampleService(): ServiceInterface = ExampleService()

    @Bean
    @ConditionalOnExpression("#{'\${service.message.type}' == 'advance'}")
    fun advanceService(): ServiceInterface = AdvanceService()
}

@Controller
class FirstController{
    @Autowired
    lateinit var service: ServiceInterface

    @RequestMapping(value = ["/user"], method = [RequestMethod.GET])
    @ResponseBody
    fun hello() = service.getHello()
}

fun main(args: Array<String>) {
    runApplication<KotlinMsaApplication>(*args)
}
