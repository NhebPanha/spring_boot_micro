package com.example.spring_micro.mapper

import com.example.spring_micro.dto.Order.OrderItemResponse
import com.example.spring_micro.dto.Order.OrderResponse
import com.example.spring_micro.entity.Order
import com.example.spring_micro.entity.OrderItem

fun OrderItem.toResponse(): OrderItemResponse {
    return OrderItemResponse(
        id = id,
        productId = product.id,
        productName = product.name,
        quantity = quantity,
        price = price,
        subtotal = price * quantity
    )
}

fun Order.toResponse(): OrderResponse {
    return OrderResponse(
        id = id,
        userId = user.id,
        status = status.name,
        totalAmount = totalAmount,
        shippingAddress = shippingAddress,
        items = items.map { it.toResponse() },
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
