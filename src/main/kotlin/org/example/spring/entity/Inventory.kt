package com.example.spring_micro.entity

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "inventory")
data class Inventory(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false, unique = true)
    val product: Product,

    var quantityAvailable: Int,
    var reorderLevel: Int = 10,

    val lastUpdated: LocalDateTime = LocalDateTime.now()
)
