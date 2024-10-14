package com.shreyjain.financeApp.services

import io.jsonwebtoken.Claims
import org.springframework.security.core.userdetails.UserDetails
import java.util.Date

interface TokenService {

    fun generateToken(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any> = emptyMap()
    ): String

    fun extractEmail(token: String): String?

    fun isExpired(token: String): Boolean

    fun isValid(token: String, userDetails: UserDetails): Boolean

    fun getAllClaims(token: String): Claims

    fun getExpirationTime(token: String): Long

}