package com.example.spring_micro.service

import com.example.spring_micro.dto.Notification.NotificationRequest
import com.example.spring_micro.dto.Notification.NotificationResponse
import com.example.spring_micro.entity.Notification
import com.example.spring_micro.entity.NotificationType
import com.example.spring_micro.mapper.toResponse
import com.example.spring_micro.repository.NotificationRepository
import com.example.spring_micro.repository.UserRepository
import com.example.spring_micro.service.notification.NotificationSender
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class NotificationService(
    private val notificationRepository: NotificationRepository,
    private val userRepository: UserRepository,
    senders: List<NotificationSender>
) {

    private val log = LoggerFactory.getLogger(javaClass)

    private val senderByType: Map<NotificationType, NotificationSender> =
        senders.associateBy { it.type }

    fun sendNotification(request: NotificationRequest): NotificationResponse {
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User not found") }

        val type = runCatching { NotificationType.valueOf(request.type.uppercase()) }
            .getOrElse { throw RuntimeException("Invalid notification type: ${request.type}") }

        val notification = Notification(
            user = user,
            type = type,
            title = request.title,
            message = request.message
        )

        val saved = notificationRepository.save(notification)
        dispatch(saved)
        return saved.toResponse()
    }

    private fun dispatch(notification: Notification) {
        val sender = senderByType[notification.type]
            ?: run {
                log.warn("No sender registered for type ${notification.type}; notification id={} stored only", notification.id)
                return
            }
        runCatching { sender.send(notification) }
            .onFailure { log.error("Failed to deliver notification id=${notification.id} via ${notification.type}", it) }
    }

    fun getAllForUser(userId: Long): List<NotificationResponse> {
        return notificationRepository.findByUserId(userId).map { it.toResponse() }
    }

    fun getUnreadForUser(userId: Long): List<NotificationResponse> {
        return notificationRepository.findByUserIdAndIsReadFalse(userId).map { it.toResponse() }
    }

    fun getUnreadCount(userId: Long): Long {
        return notificationRepository.countByUserIdAndIsReadFalse(userId)
    }

    fun getById(id: Long): NotificationResponse {
        val notification = notificationRepository.findById(id)
            .orElseThrow { RuntimeException("Notification not found") }
        return notification.toResponse()
    }

    fun markAsRead(id: Long): NotificationResponse {
        val notification = notificationRepository.findById(id)
            .orElseThrow { RuntimeException("Notification not found") }

        notification.isRead = true
        notification.updatedAt = LocalDateTime.now()
        return notificationRepository.save(notification).toResponse()
    }

    fun delete(id: Long) {
        if (!notificationRepository.existsById(id)) {
            throw RuntimeException("Notification not found")
        }
        notificationRepository.deleteById(id)
    }
}
