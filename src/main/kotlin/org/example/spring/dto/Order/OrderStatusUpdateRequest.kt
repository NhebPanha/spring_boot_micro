package com.example.spring_micro.dto.Order

import jakarta.validation.constraints.NotBlank

data class OrderStatusUpdateRequest(
    @field:NotBlank
    val status: String
)
