package com.example.spring_micro.service

import com.example.spring_micro.dto.Order.OrderRequest
import com.example.spring_micro.dto.Order.OrderResponse
import com.example.spring_micro.entity.Order
import com.example.spring_micro.entity.OrderItem
import com.example.spring_micro.entity.OrderStatus
import com.example.spring_micro.mapper.toResponse
import com.example.spring_micro.repository.OrderRepository
import com.example.spring_micro.repository.ProductRepository
import com.example.spring_micro.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class OrderService(
    private val orderRepository: OrderRepository,
    private val userRepository: UserRepository,
    private val productRepository: ProductRepository
) {

    @Transactional
    fun createOrder(request: OrderRequest): OrderResponse {
        val user = userRepository.findById(request.userId)
            .orElseThrow { RuntimeException("User not found") }

        val order = Order(user = user, shippingAddress = request.shippingAddress)

        var total = 0.0
        request.items.forEach { itemReq ->
            val product = productRepository.findById(itemReq.productId)
                .orElseThrow { RuntimeException("Product ${itemReq.productId} not found") }

            if (product.quantity < itemReq.quantity) {
                throw RuntimeException("Insufficient stock for product ${product.name}")
            }

            val unitPrice = product.discountPrice ?: product.price
            val item = OrderItem(
                order = order,
                product = product,
                quantity = itemReq.quantity,
                price = unitPrice
            )
            order.items.add(item)
            total += unitPrice * itemReq.quantity

            product.quantity -= itemReq.quantity
            product.updatedAt = LocalDateTime.now()
            productRepository.save(product)
        }

        order.totalAmount = total
        return orderRepository.save(order).toResponse()
    }

    fun getAllOrders(): List<OrderResponse> {
        return orderRepository.findAll().map { it.toResponse() }
    }

    fun getOrderById(id: Long): OrderResponse {
        val order = orderRepository.findById(id)
            .orElseThrow { RuntimeException("Order not found") }
        return order.toResponse()
    }

    fun getOrdersByUser(userId: Long): List<OrderResponse> {
        return orderRepository.findByUserId(userId).map { it.toResponse() }
    }

    fun updateStatus(id: Long, statusName: String): OrderResponse {
        val order = orderRepository.findById(id)
            .orElseThrow { RuntimeException("Order not found") }

        val newStatus = runCatching { OrderStatus.valueOf(statusName.uppercase()) }
            .getOrElse { throw RuntimeException("Invalid order status: $statusName") }

        order.status = newStatus
        order.updatedAt = LocalDateTime.now()
        return orderRepository.save(order).toResponse()
    }

    @Transactional
    fun cancelOrder(id: Long): OrderResponse {
        val order = orderRepository.findById(id)
            .orElseThrow { RuntimeException("Order not found") }

        if (order.status == OrderStatus.DELIVERED || order.status == OrderStatus.SHIPPED) {
            throw RuntimeException("Cannot cancel an order that is ${order.status}")
        }

        order.items.forEach { item ->
            val product = item.product
            product.quantity += item.quantity
            product.updatedAt = LocalDateTime.now()
            productRepository.save(product)
        }

        order.status = OrderStatus.CANCELLED
        order.updatedAt = LocalDateTime.now()
        return orderRepository.save(order).toResponse()
    }
}
