package com.example.spring_micro.dto
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class UserRequest(
    @field:NotBlank
    val username: String,

    @field:Email
    val email: String,

    @field:NotBlank
    val password: String
)