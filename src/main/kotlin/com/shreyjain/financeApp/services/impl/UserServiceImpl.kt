package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.ValidationException
import com.shreyjain.financeApp.controllers.user.UserResponse
import com.shreyjain.financeApp.domain.dto.UserDto
import com.shreyjain.financeApp.domain.models.UserModel
import com.shreyjain.financeApp.domain.validators.UserValidator
import com.shreyjain.financeApp.repository.UserRepository
import com.shreyjain.financeApp.services.UserService
import com.shreyjain.financeApp.toUserModel
import com.shreyjain.financeApp.toUserResponse
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val userValidator: UserValidator,
    private val encoder: PasswordEncoder
) : UserService {

    override fun findUserByEmail(email: String): UserModel? {
        return userRepository.findByEmail(email)
    }

    override fun doesEmailExist(email: String): Boolean {
        return userRepository.existsByEmail(email)
    }

    override fun findUserByUsername(username: String): UserModel? {
        return userRepository.findByUsername(username)
    }

    override fun doesUsernameExist(username: String): Boolean {
        return userRepository.existsByUsername(username)
    }

    override fun findUsersByRole(role: String): List<UserModel> {
        return userRepository.findByRole(role)
    }

    override fun createUser(user: UserModel): UserModel {
        return userRepository.save(user)
    }

    override fun updateUser(user: UserModel): UserModel {
        return userRepository.save(user)
    }

    override fun deleteUser(userId: String) {
        userRepository.deleteById(userId)
    }

    override fun validateAndCreateUser(userDto: UserDto): Result<UserResponse> {
        val validationErrors = userValidator.validate(userDto)

        return if (validationErrors.isEmpty()) {
            try {
                val userModel = userDto.toUserModel().copy(password = encoder.encode(userDto.password))
                userRepository.save(userModel)
                Result.success(userModel.toUserResponse())

            } catch (e: Exception) {
                Result.failure(e)
            }
        } else {
            Result.failure(ValidationException(validationErrors.joinToString("; ")))
        }
    }

}
