package com.example.spring_micro.dto.Analytics

data class TopProductResponse(
    val productId: Long,
    val productName: String,
    val totalSold: Long,
    val totalRevenue: Double
)
