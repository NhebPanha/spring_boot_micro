package com.example.spring_micro.service
import org.example.spring.dto.User.UserRequest
import org.example.spring.dto.User.UserResponse
import com.example.spring_micro.mapper.toEntity
import com.example.spring_micro.mapper.toResponse
import com.example.spring_micro.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun createUser(request: UserRequest): UserResponse {
        val user = request.toEntity()
        return userRepository.save(user).toResponse()
    }

    fun getAllUsers(): List<UserResponse> {
        return userRepository.findAll().map { it.toResponse() }
    }

    fun getUserById(id: Long): UserResponse {
        val user = userRepository.findById(id)
            .orElseThrow { RuntimeException("User not found") }

        return user.toResponse()
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) {
            throw RuntimeException("User not found")
        }
        userRepository.deleteById(id)
    }
}