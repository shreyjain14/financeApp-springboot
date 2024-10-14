package com.shreyjain.financeApp.controllers.defaults

import com.shreyjain.financeApp.domain.models.DefaultsModel
import com.shreyjain.financeApp.services.DefaultsService
import com.shreyjain.financeApp.services.TokenService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/defaults/")
class DefaultsController(
    private val defaultsService: DefaultsService,
    private val tokenService: TokenService
) {

    @GetMapping
    fun getDefaults(
        @RequestHeader("Authorization") authHeader: String
    ) : DefaultsModel? {

        val email: String? = extractEmail(authHeader)

        return defaultsService.findByEmail(email!!)
    }

    @PostMapping("/payedTo")
    fun addPayedTo(
        @RequestBody defaultsRequest: DefaultsRequest,
        @RequestHeader("Authorization") authHeader: String
    ) : List<String> {

        val email: String? = extractEmail(authHeader)
        defaultsService.addPayTo(email!!, defaultsRequest.pay)

        return defaultsService.findPayTo(email)!!
    }

    @GetMapping("/payedTo")
    fun getPayedTo(
        @RequestHeader("Authorization") authHeader: String
    ) : List<String> {

        val email: String? = extractEmail(authHeader)
        return defaultsService.findPayTo(email!!)!!
    }

    @DeleteMapping("/payedTo")
    fun removePayedTo(
        @RequestBody defaultsRequest: DefaultsRequest,
        @RequestHeader("Authorization") authHeader: String
    ) : List<String> {

        val email: String? = extractEmail(authHeader)
        defaultsService.removePayTo(email!!, defaultsRequest.pay)

        return defaultsService.findPayTo(email)!!
    }

    @PostMapping("/payedFrom")
    fun addPayedFrom(
        @RequestBody defaultsRequest: DefaultsRequest,
        @RequestHeader("Authorization") authHeader: String
    ) : List<String> {

        val email: String? = extractEmail(authHeader)
        defaultsService.addPayFrom(email!!, defaultsRequest.pay)

        return defaultsService.findPayFrom(email)!!
    }

    @GetMapping("/payedFrom")
    fun getPayedFrom(
        @RequestHeader("Authorization") authHeader: String
    ) : List<String> {

        val email: String? = extractEmail(authHeader)
        return defaultsService.findPayFrom(email!!)!!
    }

    @DeleteMapping("/payedFrom")
    fun removePayedFrom(
        @RequestBody defaultsRequest: DefaultsRequest,
        @RequestHeader("Authorization") authHeader: String
    ) : List<String> {

        val email: String? = extractEmail(authHeader)
        defaultsService.removePayFrom(email!!, defaultsRequest.pay)

        return defaultsService.findPayFrom(email)!!
    }

    fun extractEmail(authHeader: String): String? {
        val token = authHeader.replace("Bearer ", "")
        val email: String? = tokenService.extractEmail(token)
        return email
    }

}