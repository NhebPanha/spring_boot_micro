package com.example.spring_micro.controller

import com.example.spring_micro.dto.User.UserRequest
import com.example.spring_micro.dto.User.UserResponse
import com.example.spring_micro.dto.User.LoginRequest
import com.example.spring_micro.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val userService: UserService
) {

    // ✅ REGISTER
    @PostMapping("/register")
    fun register(
        @Valid @RequestBody request: UserRequest
    ): UserResponse {
        return userService.register(request)
    }

    // ✅ LOGIN
    @PostMapping("/login")
    fun login(
        @Valid @RequestBody request: LoginRequest
    ): UserResponse {
        return userService.login(request)
    }

    // ✅ Get all users (ADMIN / AUTHORIZED)
    @GetMapping("/users")
    fun getAllUsers(): List<UserResponse> {
        return userService.getAllUsers()
    }
}