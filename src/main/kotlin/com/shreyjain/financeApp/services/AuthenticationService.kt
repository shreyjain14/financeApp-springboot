package com.shreyjain.financeApp.services

import com.shreyjain.financeApp.controllers.auth.AuthenticationRequest
import com.shreyjain.financeApp.controllers.auth.AuthenticationResponse

interface AuthenticationService {

    fun authentication(authRequest: AuthenticationRequest): AuthenticationResponse
    fun refreshAccessToken(token: String): String?
    fun logout(token: String): Boolean

}
