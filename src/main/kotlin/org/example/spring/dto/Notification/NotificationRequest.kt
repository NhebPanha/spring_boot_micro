package com.example.spring_micro.dto.Notification

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class NotificationRequest(
    @field:NotNull
    val userId: Long,

    @field:NotBlank
    val type: String,

    @field:NotBlank
    val title: String,

    @field:NotBlank
    val message: String
)
