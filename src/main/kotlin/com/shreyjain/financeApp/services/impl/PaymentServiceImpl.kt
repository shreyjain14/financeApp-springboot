package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.ValidationException
import com.shreyjain.financeApp.domain.dto.PaymentDto
import com.shreyjain.financeApp.domain.models.PaymentModel
import com.shreyjain.financeApp.domain.validators.PaymentValidator
import com.shreyjain.financeApp.repository.PaymentRepository
import com.shreyjain.financeApp.services.PaymentService
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class PaymentServiceImpl (
    private val paymentRepository: PaymentRepository,
    private val paymentValidator: PaymentValidator
) : PaymentService {

    override fun findPaymentsByUserId(userId: String, page: Int): List<PaymentModel> {
        return paymentRepository.findByUserId(userId, PageRequest.of(page - 1, 10))
    }

    override fun savePayment(paymentDto: PaymentDto): PaymentModel {

        val validationErrors = paymentValidator.validate(paymentDto)

        if (validationErrors.isNotEmpty()) {
            throw ValidationException(validationErrors.joinToString("; "))
        }

        val paymentModel = PaymentModel(
            amount = paymentDto.amount,
            currency = paymentDto.currency,
            date = paymentDto.date,
            payedFrom = paymentDto.payedFrom,
            payedTo = paymentDto.payedTo,
            userId = paymentDto.userId
        )

        return paymentRepository.save(paymentModel)

    }

    override fun deletePayment(paymentId: String) : Boolean {
        val payment = paymentRepository.findById(paymentId)

        return if (payment.isPresent) {
            paymentRepository.deleteById(paymentId)
            true
        } else {
            false
        }
    }

    override fun getPaymentUser(paymentId: String): String {
        val payment = paymentRepository.findById(paymentId)

        return if (payment.isPresent) {
            payment.get().userId
        } else {
            ""
        }
    }

}