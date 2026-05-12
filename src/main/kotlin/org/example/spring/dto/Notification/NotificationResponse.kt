package com.example.spring_micro.dto.Notification

import java.time.LocalDateTime

data class NotificationResponse(
    val id: Long,
    val userId: Long,
    val type: String,
    val title: String,
    val message: String,
    val isRead: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)
