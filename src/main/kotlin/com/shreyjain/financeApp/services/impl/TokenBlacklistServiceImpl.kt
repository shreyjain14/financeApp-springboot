package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.services.TokenBlacklistService
import org.springframework.stereotype.Service
import java.util.concurrent.ConcurrentHashMap

@Service
class TokenBlacklistServiceImpl : TokenBlacklistService {
    private val blacklistedTokens = ConcurrentHashMap<String, Long>()

    override fun blacklistToken(token: String, expirationTime: Long) {
        blacklistedTokens[token] = expirationTime
    }

    override fun isTokenBlacklisted(token: String): Boolean {
        val expirationTime = blacklistedTokens[token]
        return if (expirationTime != null) {
            if (System.currentTimeMillis() > expirationTime) {
                blacklistedTokens.remove(token)
                false
            } else {
                true
            }
        } else {
            false
        }
    }
}