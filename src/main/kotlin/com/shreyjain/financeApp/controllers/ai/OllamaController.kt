package com.shreyjain.financeApp.controllers.ai

import com.shreyjain.financeApp.domain.models.ai.ollama.Llama32Options
import com.shreyjain.financeApp.services.OllamaService
import com.shreyjain.financeApp.services.TokenService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/aiSummary")
class OllamaController(
    private val ollamaService: OllamaService,
    private val tokenService: TokenService
    ) {

    data class GenerateRequest(
        val prompt: String,
        val options: Llama32Options = Llama32Options()
    )

    data class GenerateResponse(
        val response: String
    )

    private val logger: Logger = LoggerFactory.getLogger(OllamaController::class.java)

    @GetMapping
    suspend fun generate(
        @RequestHeader("Authorization") authHeader: String
    ): ResponseEntity<GenerateResponse> {

        val token = authHeader.replace("Bearer ", "")
        val email: String? = tokenService.extractEmail(token)

        val response = ollamaService.generateResponse(email!!)
        println(response)
        return ResponseEntity.ok(GenerateResponse(response))
    }

    @GetMapping("/health")
    suspend fun healthCheck(
        @RequestHeader("Authorization") authHeader: String
    ): ResponseEntity<Map<String, Boolean>> {
        val isAvailable = ollamaService.isServiceAvailable()
        return ResponseEntity.ok(mapOf("available" to isAvailable))
    }
}