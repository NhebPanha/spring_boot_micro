package com.example.spring_micro.repository

import com.example.spring_micro.entity.Notification
import org.springframework.data.jpa.repository.JpaRepository

interface NotificationRepository : JpaRepository<Notification, Long> {
    fun findByUserId(userId: Long): List<Notification>
    fun findByUserIdAndIsReadFalse(userId: Long): List<Notification>
    fun countByUserIdAndIsReadFalse(userId: Long): Long
}
