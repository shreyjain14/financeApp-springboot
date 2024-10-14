package com.shreyjain.financeApp.repository

import com.shreyjain.financeApp.domain.models.DefaultsModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DefaultsRepository : MongoRepository<DefaultsModel, String> {

    fun findByEmail(email: String): DefaultsModel?

}