package com.example.spring_micro.dto.Analytics

data class RevenueReportResponse(
    val period: String,
    val totalRevenue: Double,
    val orderCount: Long
)
