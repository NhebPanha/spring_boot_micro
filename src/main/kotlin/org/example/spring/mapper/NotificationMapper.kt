package com.example.spring_micro.mapper

import com.example.spring_micro.dto.Notification.NotificationResponse
import com.example.spring_micro.entity.Notification

fun Notification.toResponse(): NotificationResponse {
    return NotificationResponse(
        id = id,
        userId = user.id,
        type = type.name,
        title = title,
        message = message,
        isRead = isRead,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
