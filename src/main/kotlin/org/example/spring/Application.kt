package com.example.spring_micro

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringMicroApplication

fun main(args: Array<String>) {
    runApplication<SpringMicroApplication>(*args)
}