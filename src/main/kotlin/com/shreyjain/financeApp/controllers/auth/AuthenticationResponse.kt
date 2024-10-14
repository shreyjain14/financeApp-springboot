package com.shreyjain.financeApp.controllers.auth

data class AuthenticationResponse (
    val accessToken: String,
    val refreshToken: String
)
