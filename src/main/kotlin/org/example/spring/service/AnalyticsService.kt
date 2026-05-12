package com.example.spring_micro.service

import com.example.spring_micro.dto.Analytics.DashboardResponse
import com.example.spring_micro.dto.Analytics.RevenueReportResponse
import com.example.spring_micro.dto.Analytics.SalesSummaryResponse
import com.example.spring_micro.dto.Analytics.TopProductResponse
import com.example.spring_micro.entity.OrderStatus
import com.example.spring_micro.repository.NotificationRepository
import com.example.spring_micro.repository.OrderItemRepository
import com.example.spring_micro.repository.OrderRepository
import com.example.spring_micro.repository.ProductRepository
import com.example.spring_micro.repository.UserRepository
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime

@Service
class AnalyticsService(
    private val orderRepository: OrderRepository,
    private val orderItemRepository: OrderItemRepository,
    private val productRepository: ProductRepository,
    private val userRepository: UserRepository,
    private val notificationRepository: NotificationRepository
) {

    fun getSalesSummary(): SalesSummaryResponse {
        return SalesSummaryResponse(
            totalOrders = orderRepository.count(),
            totalRevenue = orderRepository.sumTotalRevenue(),
            pendingOrders = orderRepository.countByStatus(OrderStatus.PENDING),
            paidOrders = orderRepository.countByStatus(OrderStatus.PAID),
            shippedOrders = orderRepository.countByStatus(OrderStatus.SHIPPED),
            deliveredOrders = orderRepository.countByStatus(OrderStatus.DELIVERED),
            cancelledOrders = orderRepository.countByStatus(OrderStatus.CANCELLED)
        )
    }

    fun getDailyRevenue(date: LocalDate = LocalDate.now()): RevenueReportResponse {
        val start = date.atStartOfDay()
        val end = date.atTime(LocalTime.MAX)
        return RevenueReportResponse(
            period = "DAILY:$date",
            totalRevenue = orderRepository.sumRevenueBetween(start, end),
            orderCount = orderRepository.countByCreatedAtBetween(start, end)
        )
    }

    fun getMonthlyRevenue(year: Int = LocalDate.now().year, month: Int = LocalDate.now().monthValue): RevenueReportResponse {
        val firstDay = LocalDate.of(year, month, 1)
        val start = firstDay.atStartOfDay()
        val end = firstDay.plusMonths(1).atStartOfDay().minusNanos(1)
        return RevenueReportResponse(
            period = "MONTHLY:$year-${month.toString().padStart(2, '0')}",
            totalRevenue = orderRepository.sumRevenueBetween(start, end),
            orderCount = orderRepository.countByCreatedAtBetween(start, end)
        )
    }

    fun getTopProducts(limit: Int = 5): List<TopProductResponse> {
        return orderItemRepository.findTopSellingProducts(PageRequest.of(0, limit))
            .map {
                TopProductResponse(
                    productId = it.productId,
                    productName = it.productName,
                    totalSold = it.totalSold,
                    totalRevenue = it.totalRevenue
                )
            }
    }

    fun getActiveUsers(): Long {
        return userRepository.count()
    }

    fun getDashboard(): DashboardResponse {
        val today = LocalDate.now()
        val startOfDay = today.atStartOfDay()
        val endOfDay = today.atTime(LocalTime.MAX)

        val firstOfMonth = today.withDayOfMonth(1).atStartOfDay()
        val startOfNextMonth = today.withDayOfMonth(1).plusMonths(1).atStartOfDay()

        return DashboardResponse(
            totalUsers = userRepository.count(),
            totalProducts = productRepository.count(),
            totalOrders = orderRepository.count(),
            totalRevenue = orderRepository.sumTotalRevenue(),
            totalNotifications = notificationRepository.count(),
            dailyOrders = orderRepository.countByCreatedAtBetween(startOfDay, endOfDay),
            monthlyRevenue = orderRepository.sumRevenueBetween(firstOfMonth, startOfNextMonth.minusNanos(1))
        )
    }
}
