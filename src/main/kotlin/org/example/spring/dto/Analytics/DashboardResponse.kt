package com.example.spring_micro.dto.Analytics

data class DashboardResponse(
    val totalUsers: Long,
    val totalProducts: Long,
    val totalOrders: Long,
    val totalRevenue: Double,
    val totalNotifications: Long,
    val dailyOrders: Long,
    val monthlyRevenue: Double
)
