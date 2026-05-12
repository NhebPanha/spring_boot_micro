package com.example.spring_micro.entity

import jakarta.persistence.*

@Entity
@Table(name = "product_images")
data class ProductImage(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @Column(nullable = false)
    var imageUrl: String,

    var isPrimary: Boolean = false,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    val product: Product
)
