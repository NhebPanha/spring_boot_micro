package com.example.spring_micro.service

import org.example.spring.dto.User.UserRequest
import org.example.spring.dto.User.UserResponse
import com.example.spring_micro.model.User
import com.example.spring_micro.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository
) {

    // REGISTER
    fun register(request: UserRequest): UserResponse {

        if (userRepository.findByEmail(request.email) != null) {
            throw RuntimeException("Email already exists")
        }

        val user = User(
            username = request.username,
            email = request.email,
            password = request.password
        )

        val saved = userRepository.save(user)

        return UserResponse(
            id = saved.id,
            username = saved.username,
            email = saved.email
        )
    }

    // LOGIN
    fun login(request: UserRequest): UserResponse {

        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException("User not found")

        if (user.password != request.password) {
            throw RuntimeException("Invalid password")
        }

        return UserResponse(
            id = user.id,
            username = user.username,
            email = user.email
        )
    }

    // GET ALL USERS
    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { user ->
            UserResponse(
                id = user.id,
                username = user.username,
                email = user.email
            )
        }
    }
}