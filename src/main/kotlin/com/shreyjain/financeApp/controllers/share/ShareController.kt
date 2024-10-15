package com.shreyjain.financeApp.controllers.share

import com.shreyjain.financeApp.domain.models.PaymentModel
import com.shreyjain.financeApp.services.PaymentService
import com.shreyjain.financeApp.services.ShareService
import com.shreyjain.financeApp.services.TokenService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/share/")
class ShareController(
    private val shareService: ShareService,
    private val tokenService: TokenService,
    private val paymentService: PaymentService
) {

    @GetMapping
    fun getShares(
        @RequestHeader("Authorization") authHeader: String
    ) : ResponseEntity<List<String>> {
        val token = authHeader.replace("Bearer ", "")
        val userEmail: String? = tokenService.extractEmail(token)

        return ResponseEntity.ok(shareService.findShares(userEmail!!)!!)
    }

    @GetMapping("/check")
    fun checkShare(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam("email") email: String,
        @RequestParam("page", defaultValue = "1") page: Int
    ) : ResponseEntity<List<PaymentModel>> {
        val token = authHeader.replace("Bearer ", "")
        val userEmail: String? = tokenService.extractEmail(token)

        val shares = shareService.findShares(email)

        if ( shares != null && shares.contains(userEmail)) {
            return ResponseEntity.ok(paymentService.findPaymentsByUserId(email, page))
        } else {
            throw Exception("You do not have access to $email's data")
        }

    }

    @PostMapping
    fun addShare(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam("email") email: String
    ) : ResponseEntity<Any> {
        val token = authHeader.replace("Bearer ", "")
        val userEmail: String? = tokenService.extractEmail(token)

        val shares = shareService.findShares(userEmail!!)

        if ( !shares!!.contains(email) ) {
            return ResponseEntity.ok(shareService.addShares(userEmail, email)!!.allowedUsers)
        } else {
            throw Exception("$email is already shared")
        }
    }

    @DeleteMapping
    fun removeShare(
        @RequestHeader("Authorization") authHeader: String,
        @RequestParam("email") email: String
    ) : ResponseEntity<Any> {
        val token = authHeader.replace("Bearer ", "")
        val userEmail: String? = tokenService.extractEmail(token)

        val shares = shareService.findShares(userEmail!!)

        if ( shares!!.contains(email) ) {
            return ResponseEntity.ok(shareService.removeShares(userEmail, email)!!.allowedUsers)
        } else {
            throw Exception("$email is not shared")
        }
    }

}