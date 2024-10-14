package com.shreyjain.financeApp.domain.models

import org.springframework.data.annotation.Id

data class TokenModel(
    val token: String,
    @Id
    val email: String?
)
