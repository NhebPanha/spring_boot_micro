package com.example.spring_micro.service

import com.example.spring_micro.dto.ProductRequest
import com.example.spring_micro.dto.ProductResponse
import com.example.spring_micro.mapper.toEntity
import com.example.spring_micro.mapper.toResponse
import com.example.spring_micro.repository.ProductRepository
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class ProductService(private val productRepository: ProductRepository) {

    fun createProduct(request: ProductRequest): ProductResponse {
        val product = request.toEntity()
        return productRepository.save(product).toResponse()
    }

    fun getAllProducts(): List<ProductResponse> {
        return productRepository.findAll().map { it.toResponse() }
    }

    fun getProductById(id: Long): ProductResponse {
        val product = productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }

        return product.toResponse()
    }

    fun deleteProduct(id: Long) {
        if (!productRepository.existsById(id)) {
            throw RuntimeException("Product not found")
        }
        productRepository.deleteById(id)
    }

    fun updateProduct(id: Long, request: ProductRequest): ProductResponse {
        val product = productRepository.findById(id)
            .orElseThrow { RuntimeException("Product not found") }

        product.name = request.name
        product.description = request.description
        product.price = request.price
        product.discountPrice = request.discountPrice
        product.quantity = request.quantity
        product.sku = request.sku
        product.category = request.category
        product.brand = request.brand
        product.imageUrl = request.imageUrl
        product.weight = request.weight
        product.size = request.size
        product.color = request.color
        product.rating = request.rating
        product.isActive = request.isActive
        product.updatedAt = LocalDateTime.now()

        return productRepository.save(product).toResponse()
    }
}