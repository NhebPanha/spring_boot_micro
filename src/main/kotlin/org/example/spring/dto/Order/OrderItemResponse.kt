package com.example.spring_micro.dto.Order

data class OrderItemResponse(
    val id: Long,
    val productId: Long,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val subtotal: Double
)
