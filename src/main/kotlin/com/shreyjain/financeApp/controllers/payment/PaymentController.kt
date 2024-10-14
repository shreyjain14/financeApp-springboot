package com.shreyjain.financeApp.controllers.payment

import com.shreyjain.financeApp.domain.dto.PaymentDto
import com.shreyjain.financeApp.domain.models.PaymentModel
import com.shreyjain.financeApp.services.PaymentService
import com.shreyjain.financeApp.services.TokenService
import com.shreyjain.financeApp.toDto
import io.jsonwebtoken.Claims
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

@RestController
@RequestMapping("/api/payment")
class PaymentController (
    private val paymentService: PaymentService,
    private val tokenService: TokenService
) {

    @GetMapping
    fun getPayments(
        @RequestParam("page", defaultValue = "1") page: Int,
        @RequestHeader("Authorization") authHeader: String
    ) : ResponseEntity<List<PaymentModel>> {

        val token = authHeader.replace("Bearer ", "")
        val email: String? = tokenService.extractEmail(token)

        return ResponseEntity.ok(paymentService.findPaymentsByUserId(email!!, page))

    }

    @PostMapping
    fun savePayment(
        @RequestBody paymentRequest: PaymentRequest,
        @RequestHeader("Authorization") authHeader: String
    ) : ResponseEntity<PaymentModel> {

        val currentDateTime = LocalDateTime.now(ZoneId.systemDefault())
        val formattedDateTime = currentDateTime.format(
            DateTimeFormatter.ISO_LOCAL_DATE_TIME
        )

        val token = authHeader.replace("Bearer ", "")
        val email: String? = tokenService.extractEmail(token)

        return ResponseEntity.ok(paymentService.savePayment(
            paymentRequest.toDto(email!!, formattedDateTime)
        ))

    }

    @DeleteMapping
    fun deletePayment(
        @RequestParam("paymentId") paymentId: String,
        @RequestHeader("Authorization") authHeader: String
    ) : ResponseEntity<Boolean> {

        val token = authHeader.replace("Bearer ", "")
        val email: String? = tokenService.extractEmail(token)


        if (paymentId.isBlank()) {
            return ResponseEntity.badRequest().body(false)
        }

        paymentService.getPaymentUser(paymentId).let {
            if (it != email) {
                return ResponseEntity.status(403).body(false)
            }
        }

        return ResponseEntity.ok(paymentService.deletePayment(paymentId))

    }

}