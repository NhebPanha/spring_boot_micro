package com.example.spring_micro.mapper

import com.example.spring_micro.dto.UserRequest
import com.example.spring_micro.dto.UserResponse
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