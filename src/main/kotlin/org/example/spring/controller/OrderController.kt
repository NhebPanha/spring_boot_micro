package com.example.spring_micro.controller

import com.example.spring_micro.dto.Order.OrderRequest
import com.example.spring_micro.dto.Order.OrderResponse
import com.example.spring_micro.dto.Order.OrderStatusUpdateRequest
import com.example.spring_micro.service.OrderService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/orders")
class OrderController(private val orderService: OrderService) {

    @PostMapping
    fun create(@Valid @RequestBody request: OrderRequest): OrderResponse {
        return orderService.createOrder(request)
    }

    @GetMapping
    fun getAll(): List<OrderResponse> {
        return orderService.getAllOrders()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): OrderResponse {
        return orderService.getOrderById(id)
    }

    @GetMapping("/user/{userId}")
    fun getByUser(@PathVariable userId: Long): List<OrderResponse> {
        return orderService.getOrdersByUser(userId)
    }

    @PutMapping("/{id}/status")
    fun updateStatus(
        @PathVariable id: Long,
        @Valid @RequestBody request: OrderStatusUpdateRequest
    ): OrderResponse {
        return orderService.updateStatus(id, request.status)
    }

    @DeleteMapping("/{id}")
    fun cancel(@PathVariable id: Long): OrderResponse {
        return orderService.cancelOrder(id)
    }
}
