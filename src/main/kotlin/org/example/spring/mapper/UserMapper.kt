package com.example.spring_micro.mapper
import org.example.spring.dto.Users.UserRequest
import org.example.spring.dto.Users.UserResponse
import com.example.spring_micro.model.User

fun UserRequest.toEntity(): User {
    return User(
        username = username,
        email = email,
        password = password
    )
}

fun User.toResponse(): UserResponse {
    return UserResponse(
        id = id,
        username = username,
        email = email
    )
}