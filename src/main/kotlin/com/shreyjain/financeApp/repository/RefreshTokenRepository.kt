package com.shreyjain.financeApp.repository

import com.shreyjain.financeApp.domain.models.TokenModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface RefreshTokenRepository : MongoRepository<TokenModel, String> {

    fun findByToken(token: String): TokenModel?
    fun deleteByToken(token: String)
    fun findByEmail(email: String): TokenModel?
    fun deleteByEmail(email: String)

}