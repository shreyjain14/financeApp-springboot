package com.shreyjain.financeApp.domain.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class ShareModel(
    @Id
    val email: String,
    val allowedUsers: MutableList<String>,
)