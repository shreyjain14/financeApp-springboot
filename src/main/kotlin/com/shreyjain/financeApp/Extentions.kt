package com.shreyjain.financeApp

import com.shreyjain.financeApp.controllers.user.UserResponse
import com.shreyjain.financeApp.controllers.payment.PaymentRequest
import com.shreyjain.financeApp.domain.dto.PaymentDto
import com.shreyjain.financeApp.domain.dto.UserDto
import com.shreyjain.financeApp.domain.models.PaymentModel
import com.shreyjain.financeApp.domain.models.Role
import com.shreyjain.financeApp.domain.models.UserModel

fun UserDto.toUserModel() = UserModel(
    id = null,
    username = this.username,
    email = this.email,
    password = this.password,
    role = if (this.role == "CHILD") Role.CHILD else Role.GUARDIAN
)

fun UserModel.toUserResponse() = UserResponse(
    id = this.id!!,
    username = this.username,
    email = this.email,
    role = this.role.toString(),
    verified = this.verified
)

fun PaymentDto.toPaymentModel() = PaymentModel(
    id = null,
    amount = this.amount,
    currency = this.currency,
    date = this.date,
    payedFrom = this.payedFrom,
    payedTo = this.payedTo,
    userId = this.userId
)

fun PaymentRequest.toDto(userId: String, date: String) = PaymentDto(
    amount = this.amount,
    currency = this.currency,
    date = date,
    payedFrom = this.payedFrom,
    payedTo = this.payedTo,
    userId = userId
)

class ValidationException(message: String) : RuntimeException(message)
