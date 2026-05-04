package com.example.spring_micro.controller
import org.example.spring.dto.User.UserRequest
import org.example.spring.dto.User.UserResponse
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
        @Valid @RequestBody request: UserRequest
    ): UserResponse {
        return userService.login(request)
    }

    // ✅ Get User
    @GetMapping("/users")
    fun getAllUsers(): List<UserResponse> {
        return userService.getAllUsers()
    }
}