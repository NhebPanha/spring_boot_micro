package com.example.spring_micro.dto.Order

import jakarta.validation.Valid
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull

data class OrderRequest(
    @field:NotNull
    val userId: Long,

    @field:Valid
    @field:NotEmpty
    val items: List<OrderItemRequest>,

    val shippingAddress: String? = null
)
