package com.example.spring_micro.dto.User

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String,
    val role: String,
    val token: String
)