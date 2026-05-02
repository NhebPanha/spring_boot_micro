package com.example.spring_micro.controller
import com.example.spring_micro.dto.ProductRequest
import com.example.spring_micro.dto.ProductResponse
import com.example.spring_micro.service.ProductService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/products")
class ProductController(private val productService: ProductService) {

    @PostMapping
    fun create(@Valid @RequestBody request: ProductRequest): ProductResponse {
        return productService.createProduct(request)
    }

    @GetMapping
    fun getAll(): List<ProductResponse> {
        return productService.getAllProducts()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): ProductResponse {
        return productService.getProductById(id)
    }

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long) {
        productService.deleteProduct(id)
    }

    @PutMapping("/{id}")
    fun update(
        @PathVariable id: Long,
        @Valid @RequestBody request: ProductRequest
    ): ProductResponse {
        return productService.updateProduct(id, request)
    }
}