package com.shreyjain.financeApp.controllers.ai

import com.shreyjain.financeApp.domain.models.ai.ollama.Llama32Options
import com.shreyjain.financeApp.services.OllamaService
import org.springframework.web.bind.annotation.*
import org.springframework.http.ResponseEntity

@RestController
@RequestMapping("/api/aiSummary")
class OllamaController(private val ollamaService: OllamaService) {

    data class GenerateRequest(
        val prompt: String,
        val options: Llama32Options = Llama32Options()
    )

    data class GenerateResponse(
        val response: String
    )

    @PostMapping
    suspend fun generate(
        @RequestBody request: GenerateRequest,
        @RequestHeader("Authorization") authHeader: String
    ): ResponseEntity<GenerateResponse> {
        val response = ollamaService.generateResponse(request.prompt, request.options)
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