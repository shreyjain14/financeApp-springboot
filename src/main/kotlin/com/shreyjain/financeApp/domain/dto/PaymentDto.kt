package com.shreyjain.financeApp.domain.dto

data class PaymentDto(
    var amount: Double,
    var currency: String,
    var date: String,
    var payedFrom: String,
    var payedTo: String,
    var userId: String
)
