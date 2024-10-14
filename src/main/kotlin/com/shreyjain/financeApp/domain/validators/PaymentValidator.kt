package com.shreyjain.financeApp.domain.validators

import com.shreyjain.financeApp.domain.dto.PaymentDto
import com.shreyjain.financeApp.repository.PaymentRepository
import org.springframework.stereotype.Component

@Component
class PaymentValidator (private val paymentRepository: PaymentRepository) {

    fun validate(paymentDto: PaymentDto) : List<String> {
        val errors = mutableListOf<String>()

        if (paymentDto.amount < 0) errors.add("Amount must be positive")
        if (paymentDto.currency.isBlank()) errors.add("Currency must not be blank")
        if (paymentDto.payedTo.isBlank()) errors.add("Payed to must not be blank")
        if (paymentDto.payedFrom.isBlank()) errors.add("Payed from must not be blank")

        return errors

    }

}