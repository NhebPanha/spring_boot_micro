package com.example.spring_micro.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "products")
data class Product(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var name: String,

    @Column(columnDefinition = "TEXT")
    var description: String? = null,

    @Column(nullable = false)
    var price: Double,

    var discountPrice: Double? = null,

    var quantity: Int = 0,

    @Column(unique = true)
    var sku: String? = null,

    var category: String? = null,
    var brand: String? = null,

    var imageUrl: String? = null,

    var weight: Double? = null,
    var size: String? = null,
    var color: String? = null,

    var rating: Double = 0.0,

    var isActive: Boolean = true,

    val createdAt: LocalDateTime = LocalDateTime.now(),
    var updatedAt: LocalDateTime = LocalDateTime.now()
)