package com.example.spring_micro.dto.Analytics

data class SalesSummaryResponse(
    val totalOrders: Long,
    val totalRevenue: Double,
    val pendingOrders: Long,
    val paidOrders: Long,
    val shippedOrders: Long,
    val deliveredOrders: Long,
    val cancelledOrders: Long
)
