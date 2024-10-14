package com.shreyjain.financeApp.domain.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class PaymentModel(
    @Id
    var id: String? = null,
    var amount: Double,
    var currency: String,
    var date: String,
    var payedFrom: String,
    var payedTo: String,
    var userId: String
)
