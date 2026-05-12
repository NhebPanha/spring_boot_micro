package com.example.spring_micro.controller

import com.example.spring_micro.dto.Notification.NotificationRequest
import com.example.spring_micro.dto.Notification.NotificationResponse
import com.example.spring_micro.service.NotificationService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/notifications")
class NotificationController(private val notificationService: NotificationService) {

    @PostMapping
    fun send(@Valid @RequestBody request: NotificationRequest): NotificationResponse {
        return notificationService.sendNotification(request)
    }

    @GetMapping("/user/{userId}")
    fun getAllForUser(@PathVariable userId: Long): List<NotificationResponse> {
        return notificationService.getAllForUser(userId)
    }

    @GetMapping("/user/{userId}/unread")
    fun getUnreadForUser(@PathVariable userId: Long): List<NotificationResponse> {
        return notificationService.getUnreadForUser(userId)
    }

    @GetMapping("/user/{userId}/unread/count")
    fun getUnreadCount(@PathVariable userId: Long): Map<String, Long> {
        return mapOf("count" to notificationService.getUnreadCount(userId))
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): NotificationResponse {
        return notificationService.getById(id)
    }

    @PutMapping("/{id}/read")
    fun markAsRead(@PathVariable id: Long): NotificationResponse {
        return notificationService.markAsRead(id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        notificationService.delete(id)
    }
}
