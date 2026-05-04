package org.example.spring.dto.User

data class UserResponse(
    val id: Long,
    val username: String,
    val email: String
)