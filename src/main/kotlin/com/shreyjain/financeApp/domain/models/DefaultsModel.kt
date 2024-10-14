package com.shreyjain.financeApp.domain.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class DefaultsModel(
    @Id
    val email: String,
    val payedTo: MutableList<String>,
    val payedFrom: MutableList<String>,
)
