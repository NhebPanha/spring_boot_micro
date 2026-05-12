package com.example.spring_micro.dto.Order

import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotNull

data class OrderItemRequest(
    @field:NotNull
    val productId: Long,

    @field:NotNull
    @field:Min(1)
    val quantity: Int
)
