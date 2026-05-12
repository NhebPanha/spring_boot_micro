package com.example.spring_micro.controller

import com.example.spring_micro.dto.Analytics.DashboardResponse
import com.example.spring_micro.dto.Analytics.RevenueReportResponse
import com.example.spring_micro.dto.Analytics.SalesSummaryResponse
import com.example.spring_micro.dto.Analytics.TopProductResponse
import com.example.spring_micro.service.AnalyticsService
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

@RestController
@RequestMapping("/api/analytics")
class AnalyticsController(private val analyticsService: AnalyticsService) {

    @GetMapping("/sales")
    fun getSalesSummary(): SalesSummaryResponse {
        return analyticsService.getSalesSummary()
    }

    @GetMapping("/revenue/daily")
    fun getDailyRevenue(@RequestParam(required = false) date: String?): RevenueReportResponse {
        val target = date?.let { LocalDate.parse(it) } ?: LocalDate.now()
        return analyticsService.getDailyRevenue(target)
    }

    @GetMapping("/revenue/monthly")
    fun getMonthlyRevenue(
        @RequestParam(required = false) year: Int?,
        @RequestParam(required = false) month: Int?
    ): RevenueReportResponse {
        val now = LocalDate.now()
        return analyticsService.getMonthlyRevenue(year ?: now.year, month ?: now.monthValue)
    }

    @GetMapping("/top-products")
    fun getTopProducts(@RequestParam(defaultValue = "5") limit: Int): List<TopProductResponse> {
        return analyticsService.getTopProducts(limit)
    }

    @GetMapping("/active-users")
    fun getActiveUsers(): Map<String, Long> {
        return mapOf("activeUsers" to analyticsService.getActiveUsers())
    }

    @GetMapping("/dashboard")
    fun getDashboard(): DashboardResponse {
        return analyticsService.getDashboard()
    }
}
