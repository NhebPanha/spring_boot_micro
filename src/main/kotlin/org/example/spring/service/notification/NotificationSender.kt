package com.example.spring_micro.service.notification

import com.example.spring_micro.entity.Notification
import com.example.spring_micro.entity.NotificationType

interface NotificationSender {
    val type: NotificationType
    fun send(notification: Notification)
}
