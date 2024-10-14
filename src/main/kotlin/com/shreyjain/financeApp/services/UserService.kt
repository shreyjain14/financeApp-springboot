package com.shreyjain.financeApp.services

import com.shreyjain.financeApp.controllers.user.UserResponse
import com.shreyjain.financeApp.domain.dto.UserDto
import com.shreyjain.financeApp.domain.models.UserModel

interface UserService {
    fun findUserByEmail(email: String): UserModel?
    fun doesEmailExist(email: String): Boolean
    fun findUserByUsername(username: String): UserModel?
    fun doesUsernameExist(username: String): Boolean
    fun findUsersByRole(role: String): List<UserModel>
    fun createUser(user: UserModel): UserModel
    fun updateUser(user: UserModel): UserModel
    fun deleteUser(userId: String)
    fun validateAndCreateUser(userDto: UserDto): Result<UserResponse>
}