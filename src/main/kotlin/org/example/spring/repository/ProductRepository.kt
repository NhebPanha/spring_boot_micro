package com.example.spring_micro.repository

import com.example.spring_micro.entity.Product
import org.springframework.data.jpa.repository.JpaRepository
interface ProductRepository : JpaRepository<Product, Long>