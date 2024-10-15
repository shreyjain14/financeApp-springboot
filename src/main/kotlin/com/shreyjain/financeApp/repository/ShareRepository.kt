package com.shreyjain.financeApp.repository

import com.shreyjain.financeApp.domain.models.ShareModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ShareRepository : MongoRepository<ShareModel, String> {

    fun findByEmail(email: String): ShareModel?

}