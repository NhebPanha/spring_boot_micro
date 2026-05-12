package com.example.spring_micro.service

import com.example.spring_micro.dto.Payment.PaymentRequest
import com.example.spring_micro.dto.Payment.PaymentResponse
import com.example.spring_micro.entity.OrderStatus
import com.example.spring_micro.entity.Payment
import com.example.spring_micro.entity.PaymentMethod
import com.example.spring_micro.entity.PaymentStatus
import com.example.spring_micro.mapper.toResponse
import com.example.spring_micro.repository.OrderRepository
import com.example.spring_micro.repository.PaymentRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.util.UUID

@Service
class PaymentService(
    private val paymentRepository: PaymentRepository,
    private val orderRepository: OrderRepository
) {

    @Transactional
    fun createPayment(request: PaymentRequest): PaymentResponse {
        val order = orderRepository.findById(request.orderId)
            .orElseThrow { RuntimeException("Order not found") }

        if (order.status == OrderStatus.CANCELLED) {
            throw RuntimeException("Cannot pay for a cancelled order")
        }

        val method = runCatching { PaymentMethod.valueOf(request.method.uppercase()) }
            .getOrElse { throw RuntimeException("Invalid payment method: ${request.method}") }

        if (request.amount < order.totalAmount) {
            throw RuntimeException("Payment amount is less than order total")
        }

        val payment = Payment(
            order = order,
            amount = request.amount,
            method = method,
            status = PaymentStatus.COMPLETED,
            transactionId = "TXN-${UUID.randomUUID()}"
        )

        val saved = paymentRepository.save(payment)

        order.status = OrderStatus.PAID
        order.updatedAt = LocalDateTime.now()
        orderRepository.save(order)

        return saved.toResponse()
    }

    fun getAllPayments(): List<PaymentResponse> {
        return paymentRepository.findAll().map { it.toResponse() }
    }

    fun getPaymentById(id: Long): PaymentResponse {
        val payment = paymentRepository.findById(id)
            .orElseThrow { RuntimeException("Payment not found") }
        return payment.toResponse()
    }

    fun getPaymentsByOrder(orderId: Long): List<PaymentResponse> {
        return paymentRepository.findByOrderId(orderId).map { it.toResponse() }
    }

    @Transactional
    fun refundPayment(id: Long): PaymentResponse {
        val payment = paymentRepository.findById(id)
            .orElseThrow { RuntimeException("Payment not found") }

        if (payment.status != PaymentStatus.COMPLETED) {
            throw RuntimeException("Only completed payments can be refunded")
        }

        payment.status = PaymentStatus.REFUNDED
        payment.updatedAt = LocalDateTime.now()

        val order = payment.order
        order.status = OrderStatus.CANCELLED
        order.updatedAt = LocalDateTime.now()
        orderRepository.save(order)

        return paymentRepository.save(payment).toResponse()
    }
}
