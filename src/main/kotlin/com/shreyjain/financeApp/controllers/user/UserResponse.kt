package com.shreyjain.financeApp.controllers.user

data class UserResponse(
    val id: String,
    val email: String,
    val username: String,
    val role: String,
    val verified: Boolean
)
