package com.example.spring_micro.mapper

import com.example.spring_micro.dto.Payment.PaymentResponse
import com.example.spring_micro.entity.Payment

fun Payment.toResponse(): PaymentResponse {
    return PaymentResponse(
        id = id,
        orderId = order.id,
        amount = amount,
        method = method.name,
        status = status.name,
        transactionId = transactionId,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
