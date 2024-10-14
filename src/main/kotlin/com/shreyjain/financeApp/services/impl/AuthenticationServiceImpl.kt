package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.config.JwtProperties
import com.shreyjain.financeApp.controllers.auth.AuthenticationRequest
import com.shreyjain.financeApp.controllers.auth.AuthenticationResponse
import com.shreyjain.financeApp.domain.models.TokenModel
import com.shreyjain.financeApp.repository.RefreshTokenRepository
import com.shreyjain.financeApp.services.AuthenticationService
import com.shreyjain.financeApp.services.CustomUserDetailsService
import com.shreyjain.financeApp.services.TokenBlacklistService
import com.shreyjain.financeApp.services.TokenService
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Service
import java.util.*

@Service
class AuthenticationServiceImpl(
    private val authenticationManager: AuthenticationManager,
    private val userDetailsService: CustomUserDetailsService,
    private val tokenService: TokenService,
    private val jwtProperties: JwtProperties,
    private val refreshTokenRepository: RefreshTokenRepository,
    private val tokenBlacklistService: TokenBlacklistService
) : AuthenticationService {

    override fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse {
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(
                authRequest.email,
                authRequest.password
            )
        )

        val user = userDetailsService.loadUserByUsername(authRequest.email)

        val accessToken = generateAccessToken(user)
        val refreshToken = generateRefreshToken(user)

        refreshTokenRepository.save(
            TokenModel(
                token = refreshToken,
                email = user.username
            )
        )

        return AuthenticationResponse(accessToken, refreshToken)

    }

    override fun refreshAccessToken(token: String): String? {
        val extractedEmail = tokenService.extractEmail(token)
        return extractedEmail?.let { email ->
            val currentUserDetails = userDetailsService.loadUserByUsername(email)
            val refreshTokenUserDetails = refreshTokenRepository.findByToken(token)
            if(!tokenService.isExpired(token) && currentUserDetails.username == refreshTokenUserDetails?.email)
                return generateAccessToken(currentUserDetails)
            else
                return null

        }
    }

    override fun logout(token: String): Boolean {
        val extractedEmail = tokenService.extractEmail(token)
        return extractedEmail?.let { email ->
            val refreshTokenUserDetails = refreshTokenRepository.findByEmail(email)
            if(refreshTokenUserDetails != null) {
                refreshTokenRepository.deleteByEmail(email)
                val expirationTime = tokenService.getExpirationTime(token)
                tokenBlacklistService.blacklistToken(token, expirationTime)
                return true
            }
            return false
        } ?: false
    }

    private fun generateRefreshToken(user: UserDetails) = tokenService.generateToken(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpiration)
    )

    private fun generateAccessToken(user: UserDetails) = tokenService.generateToken(
        userDetails = user,
        expirationDate = Date(System.currentTimeMillis() + jwtProperties.accessTokenExpiration)
    )

}