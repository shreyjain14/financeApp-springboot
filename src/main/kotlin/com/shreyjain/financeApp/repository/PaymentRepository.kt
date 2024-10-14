package com.shreyjain.financeApp.repository

import com.shreyjain.financeApp.domain.models.PaymentModel
import org.springframework.data.domain.Pageable
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface PaymentRepository : MongoRepository<PaymentModel, String> {

    fun findByUserId(userId: String, pageable: Pageable): List<PaymentModel>

}