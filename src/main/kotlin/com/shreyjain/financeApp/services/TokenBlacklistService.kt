package com.shreyjain.financeApp.services

interface TokenBlacklistService {

    fun blacklistToken(token: String, expirationTime: Long)
    fun isTokenBlacklisted(token: String): Boolean

}