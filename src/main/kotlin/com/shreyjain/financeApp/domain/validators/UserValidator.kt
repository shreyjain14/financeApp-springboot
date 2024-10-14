package com.shreyjain.financeApp.domain.validators

import com.shreyjain.financeApp.domain.dto.UserDto
import com.shreyjain.financeApp.repository.UserRepository
import org.springframework.stereotype.Component

@Component
class UserValidator(private val userRepository: UserRepository) {  // Change to use repository directly

    fun validate(userDto: UserDto): List<String> {
        val errors = mutableListOf<String>()

        // Basic validations
        if (userDto.username.isBlank()) errors.add("Name must not be blank")
        if (userDto.email.isBlank()) errors.add("Email must not be blank")
        if (!userDto.email.contains("@")) errors.add("Email must be valid")
        if (userDto.password.isBlank()) errors.add("Password must not be blank")
        if (userDto.password.length < 8) errors.add("Password must be at least 8 characters long")
        if (userDto.password.length > 64) errors.add("Password must be at most 64 characters long")

        // Repository-dependent validations
        if (userDto.email.isNotBlank() && userRepository.existsByEmail(userDto.email)) {
            errors.add("Email already exists")
        }
        if (userDto.username.isNotBlank() && userRepository.existsByUsername(userDto.username)) {
            errors.add("Username already exists")
        }

        return errors
    }
}