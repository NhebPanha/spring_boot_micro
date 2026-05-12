package com.example.spring_micro.repository

import com.example.spring_micro.entity.Payment
import com.example.spring_micro.entity.PaymentStatus
import org.springframework.data.jpa.repository.JpaRepository

interface PaymentRepository : JpaRepository<Payment, Long> {
    fun findByOrderId(orderId: Long): List<Payment>
    fun findByStatus(status: PaymentStatus): List<Payment>
}
