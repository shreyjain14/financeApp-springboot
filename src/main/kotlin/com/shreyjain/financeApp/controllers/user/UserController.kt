package com.shreyjain.financeApp.controllers.user

import com.shreyjain.financeApp.ValidationException
import com.shreyjain.financeApp.domain.dto.UserDto
import com.shreyjain.financeApp.services.DefaultsService
import com.shreyjain.financeApp.services.ShareService
import com.shreyjain.financeApp.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/auth/")
class UserController(
    private val userService: UserService,
    private val defaultsService: DefaultsService,
    private val shareService: ShareService
) {

    @PostMapping("/register")
    fun registerUser(@RequestBody userDto: UserDto): ResponseEntity<Any> {
        return userService.validateAndCreateUser(userDto).fold(
            onSuccess = { user ->
                defaultsService.createDefaults(user.email)
                shareService.createShare(user.email)
                ResponseEntity.ok(user)
            },
            onFailure = { error ->
                when (error) {
                    is ValidationException -> ResponseEntity.badRequest().body(
                        mapOf("errors" to error.message?.split("; "))
                    )
                    else -> ResponseEntity.internalServerError().body(
                        mapOf("error" to "An unexpected error occurred")
                    )
                }
            }
        )
    }

}