package com.example.spring_micro.controller

import com.example.spring_micro.dto.Payment.PaymentRequest
import com.example.spring_micro.dto.Payment.PaymentResponse
import com.example.spring_micro.service.PaymentService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/payments")
class PaymentController(private val paymentService: PaymentService) {

    @PostMapping
    fun create(@Valid @RequestBody request: PaymentRequest): PaymentResponse {
        return paymentService.createPayment(request)
    }

    @GetMapping
    fun getAll(): List<PaymentResponse> {
        return paymentService.getAllPayments()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: Long): PaymentResponse {
        return paymentService.getPaymentById(id)
    }

    @GetMapping("/order/{orderId}")
    fun getByOrder(@PathVariable orderId: Long): List<PaymentResponse> {
        return paymentService.getPaymentsByOrder(orderId)
    }

    @PostMapping("/{id}/refund")
    fun refund(@PathVariable id: Long): PaymentResponse {
        return paymentService.refundPayment(id)
    }
}
