package com.shreyjain.financeApp.controllers.payment

data class PaymentRequest(
    val amount: Double,
    val currency: String,
    val payedFrom: String,
    val payedTo: String
)
