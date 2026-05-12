package com.example.spring_micro.service

import com.example.spring_micro.dto.User.LoginRequest
import com.example.spring_micro.dto.User.UserRequest
import com.example.spring_micro.dto.User.UserResponse
import com.example.spring_micro.entity.User
import com.example.spring_micro.repository.UserRepository
import com.example.spring_micro.security.JwtUtil
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(
    private val userRepository: UserRepository,
    private val jwtUtil: JwtUtil,
    private val passwordEncoder: PasswordEncoder
) {
    // REGISTER
    fun register(request: UserRequest): UserResponse {
        if (userRepository.findByEmail(request.email) != null) {
            throw RuntimeException("Email already exists")
        }

        val user = User(
            username = request.username,
            email = request.email,
            password = passwordEncoder.encode(request.password)!!,
        )

        val saved = userRepository.save(user)
        val token = jwtUtil.generateToken(saved.email, saved.role.name)

        return UserResponse(
            id = saved.id,
            username = saved.username,
            email = saved.email,
            role = saved.role.name,
            token = token
        )
    }

    // LOGIN
    fun login(request: LoginRequest): UserResponse {
        val user = userRepository.findByEmail(request.email)
            ?: throw RuntimeException("User not found")

        if (!passwordEncoder.matches(request.password, user.password)) {
            throw RuntimeException("Invalid login credentials")
        }

        val token = jwtUtil.generateToken(user.email, user.role.name)

        return UserResponse(
            id = user.id,
            username = user.username,
            email = user.email,
            role = user.role.name,
            token = token
        )
    }

    // GET ALL USERS
    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { user ->
            UserResponse(
                id = user.id,
                username = user.username,
                email = user.email,
                role = user.role.name,
                token = ""
            )
        }
    }
}