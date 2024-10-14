package com.shreyjain.financeApp.domain.dto

data class UserDto(
    var username: String,
    var email: String,
    var password: String,
    var role: String? = null,
)
