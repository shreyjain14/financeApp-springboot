package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.repository.UserRepository
import com.shreyjain.financeApp.services.CustomUserDetailsService
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = com.shreyjain.financeApp.domain.models.UserModel

@Service
class CustomUserDetailsServiceImpl(
    private val userRepository: UserRepository
) : CustomUserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails =
        userRepository.findByEmail(username)
            ?.mapToUserDetails()
            ?: throw UsernameNotFoundException("User not found with username: $username")


    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .roles(this.role.name)
            .build()

}