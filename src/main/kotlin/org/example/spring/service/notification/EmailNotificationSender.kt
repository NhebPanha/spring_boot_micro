package com.example.spring_micro.service.notification

import com.example.spring_micro.entity.Notification
import com.example.spring_micro.entity.NotificationType
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Component
class EmailNotificationSender : NotificationSender {

    private val log = LoggerFactory.getLogger(javaClass)

    override val type: NotificationType = NotificationType.EMAIL

    override fun send(notification: Notification) {
        log.info(
            "[EMAIL] to={} subject=\"{}\" body=\"{}\"",
            notification.user.email,
            notification.title,
            notification.message
        )
    }
}
