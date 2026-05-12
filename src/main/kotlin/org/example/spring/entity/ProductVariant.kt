package com.example.spring_micro.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_variants")
data class ProductVariant(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    var name: String, // e.g., "Size", "Color"
    var value: String, // e.g., "XL", "Red"

    var additionalPrice: Double = 0.0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
)
