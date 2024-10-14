package com.shreyjain.financeApp.controllers.auth

import com.shreyjain.financeApp.repository.UserRepository
import com.shreyjain.financeApp.services.AuthenticationService
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationService: AuthenticationService
) {

    @PostMapping("/login")
    fun authenticate(@RequestBody authRequest: AuthenticationRequest) : AuthenticationResponse =
        authenticationService.authentication(authRequest)

    @PostMapping("/refresh")
    fun refreshAccessToken(@RequestBody tokenRequest: TokenRequest) : TokenResponse =
        authenticationService.refreshAccessToken(tokenRequest.token)
            ?.mapTOTokenResponse()
            ?: throw ResponseStatusException(HttpStatus.FORBIDDEN, "Invalid refresh token")

    @GetMapping("/logout")
    fun logout(
        @RequestHeader("Authorization") authHeader: String
    ) : HttpStatusCode {

        val token = authHeader.replace("Bearer ", "")

        return if(authenticationService.logout(token))
            HttpStatus.OK
        else
            HttpStatus.FORBIDDEN
    }

    private fun String.mapTOTokenResponse() : TokenResponse =
        TokenResponse(this)

}