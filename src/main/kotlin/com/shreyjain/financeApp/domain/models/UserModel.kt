package com.shreyjain.financeApp.domain.models

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class UserModel (
    @Id
    var id: String? = null,
    var username: String,
    var email: String,
    var password: String,
    var role: Role = Role.CHILD,
    var verified : Boolean = false
)

enum class Role {
    CHILD, GUARDIAN, ADMIN
}