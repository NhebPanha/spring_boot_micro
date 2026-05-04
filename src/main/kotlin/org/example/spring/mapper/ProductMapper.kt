package com.example.spring_micro.mapper
import com.example.spring_micro.dto.ProductRequest
import com.example.spring_micro.dto.ProductResponse
import com.example.spring_micro.model.Product

fun ProductRequest.toEntity(): Product {
    return Product(
        name = name,
        description = description,
        price = price,
        discountPrice = discountPrice,
        quantity = quantity,
        sku = sku,
        category = category,
        brand = brand,
        imageUrl = imageUrl,
        weight = weight,
        size = size,
        color = color,
        rating = rating,
        isActive = isActive
    )
}

fun Product.toResponse(): ProductResponse {
    return ProductResponse(
        id = id,
        name = name,
        description = description,
        price = price,
        discountPrice = discountPrice,
        quantity = quantity,
        sku = sku,
        category = category,
        brand = brand,
        imageUrl = imageUrl,
        weight = weight,
        size = size,
        color = color,
        rating = rating,
        isActive = isActive,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}