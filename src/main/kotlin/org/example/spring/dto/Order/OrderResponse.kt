package com.example.spring_micro.dto.Order

import java.time.LocalDateTime

data class OrderResponse(
    val id: Long,
    val userId: Long,
    val status: String,
    val totalAmount: Double,
    val shippingAddress: String?,
    val items: List<OrderItemResponse>,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
