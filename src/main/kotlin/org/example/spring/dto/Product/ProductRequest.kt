package com.example.spring_micro.dto

import jakarta.validation.constraints.*

data class ProductRequest(

    @field:NotBlank
    val name: String,

    val description: String? = null,

    @field:NotNull
    val price: Double,

    val discountPrice: Double? = null,

    val quantity: Int = 0,

    val sku: String? = null,

    val category: String? = null,
    val brand: String? = null,

    val imageUrl: String? = null,

    val weight: Double? = null,
    val size: String? = null,
    val color: String? = null,

    val rating: Double = 0.0,

    val isActive: Boolean = true
)