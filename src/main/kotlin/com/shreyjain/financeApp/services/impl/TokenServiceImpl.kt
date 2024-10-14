package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.config.JwtProperties
import com.shreyjain.financeApp.services.TokenService
import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.security.Keys
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class TokenServiceImpl(
    jwtProperties: JwtProperties
) : TokenService {

    private val secretKey = Keys.hmacShaKeyFor(
        jwtProperties.key.toByteArray()
    )

    override fun generateToken(
        userDetails: UserDetails,
        expirationDate: Date,
        additionalClaims: Map<String, Any>
    ): String =
        Jwts.builder()
            .claims()
            .subject(userDetails.username)
            .issuedAt(Date(System.currentTimeMillis()))
            .expiration(expirationDate)
            .add(additionalClaims)
            .and()
            .signWith(secretKey)
            .compact()

    override fun extractEmail(token: String): String? =
        getAllClaims(token)
            .subject

    override fun isExpired(token: String): Boolean =
        getAllClaims(token)
            .expiration
            .before(Date(System.currentTimeMillis()))

    override fun isValid(token: String, userDetails: UserDetails): Boolean {
        val email = extractEmail(token)

        return email == userDetails.username && !isExpired(token)
    }

    override fun getAllClaims(token: String): Claims {
        val parser = Jwts.parser()
            .verifyWith(secretKey)
            .build()

        return parser
            .parseSignedClaims(token)
            .payload
    }

    override fun getExpirationTime(token: String): Long {
        return getAllClaims(token)
            .expiration.time
    }

}