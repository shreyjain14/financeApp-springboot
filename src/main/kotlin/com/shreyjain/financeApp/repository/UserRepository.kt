package com.shreyjain.financeApp.repository

import com.shreyjain.financeApp.domain.models.UserModel
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : MongoRepository<UserModel, String> {
    fun findByEmail(email: String): UserModel?
    fun existsByEmail(email: String): Boolean

    fun findByUsername(username: String): UserModel?
    fun existsByUsername(username: String): Boolean

    fun findByRole(role: String): List<UserModel>

}