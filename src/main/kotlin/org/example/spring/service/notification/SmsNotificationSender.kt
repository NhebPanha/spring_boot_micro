package com.example.spring_micro.service.notification

import com.example.spring_micro.entity.Notification
import com.example.spring_micro.entity.NotificationType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class SmsNotificationSender : NotificationSender {

    private val log = LoggerFactory.getLogger(javaClass)

    override val type: NotificationType = NotificationType.SMS

    override fun send(notification: Notification) {
        log.info(
            "[SMS] userId={} title=\"{}\" message=\"{}\"",
            notification.user.id,
            notification.title,
            notification.message
        )
    }
}
