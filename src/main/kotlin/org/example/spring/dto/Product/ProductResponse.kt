package com.example.spring_micro.dto
import java.time.LocalDateTime
data class ProductResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val price: Double,
    val discountPrice: Double?,
    val quantity: Int,
    val sku: String?,
    val category: String?,
    val brand: String?,
    val imageUrl: String?,
    val weight: Double?,
    val size: String?,
    val color: String?,
    val rating: Double,
    val isActive: Boolean,
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime
)