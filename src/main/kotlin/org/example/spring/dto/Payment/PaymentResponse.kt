package com.example.spring_micro.dto.Payment

import java.time.LocalDateTime

data class PaymentResponse(
    val id: Long,
    val orderId: Long,
    val amount: Double,
    val method: String,
    val status: String,
    val transactionId: String?,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
