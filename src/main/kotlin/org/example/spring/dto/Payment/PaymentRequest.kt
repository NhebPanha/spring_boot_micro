package com.example.spring_micro.dto.Payment

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class PaymentRequest(
    @field:NotNull
    val orderId: Long,

    @field:NotNull
    @field:Positive
    val amount: Double,

    @field:NotBlank
    val method: String
)
