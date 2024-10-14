package com.shreyjain.financeApp.services

import com.shreyjain.financeApp.domain.dto.PaymentDto
import com.shreyjain.financeApp.domain.models.PaymentModel

interface PaymentService {

    fun findPaymentsByUserId(userId: String, page: Int): List<PaymentModel>
    fun savePayment(paymentDto: PaymentDto): PaymentModel
    fun deletePayment(paymentId: String) : Boolean
    fun getPaymentUser(paymentId: String): String

}