package com.example.spring_micro.repository

import com.example.spring_micro.entity.OrderItem
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface OrderItemRepository : JpaRepository<OrderItem, Long> {

    @Query(
        """
        SELECT oi.product.id AS productId,
               oi.product.name AS productName,
               SUM(oi.quantity) AS totalSold,
               SUM(oi.quantity * oi.price) AS totalRevenue
        FROM OrderItem oi
        WHERE oi.order.status <> 'CANCELLED'
        GROUP BY oi.product.id, oi.product.name
        ORDER BY SUM(oi.quantity) DESC
        """
    )
    fun findTopSellingProducts(pageable: Pageable): List<TopProductProjection>
}

interface TopProductProjection {
    val productId: Long
    val productName: String
    val totalSold: Long
    val totalRevenue: Double
}
