package com.example.spring_micro.repository

import com.example.spring_micro.entity.Order
import com.example.spring_micro.entity.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.time.LocalDateTime

interface OrderRepository : JpaRepository<Order, Long> {
    fun findByUserId(userId: Long): List<Order>
    fun findByStatus(status: OrderStatus): List<Order>
    fun countByStatus(status: OrderStatus): Long
    fun countByCreatedAtBetween(start: LocalDateTime, end: LocalDateTime): Long

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status <> 'CANCELLED' AND o.createdAt BETWEEN :start AND :end")
    fun sumRevenueBetween(@Param("start") start: LocalDateTime, @Param("end") end: LocalDateTime): Double

    @Query("SELECT COALESCE(SUM(o.totalAmount), 0) FROM Order o WHERE o.status <> 'CANCELLED'")
    fun sumTotalRevenue(): Double
}
