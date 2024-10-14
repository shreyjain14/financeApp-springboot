package com.shreyjain.financeApp.services

import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

interface CustomUserDetailsService : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails

}