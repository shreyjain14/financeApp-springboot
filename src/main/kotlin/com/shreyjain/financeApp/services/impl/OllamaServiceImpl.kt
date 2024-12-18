package com.shreyjain.financeApp.services.impl

import com.shreyjain.financeApp.domain.models.ai.ollama.Llama32Options
import com.shreyjain.financeApp.exception.OllamaException
import com.shreyjain.financeApp.services.OllamaService
import org.springframework.stereotype.Service
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value

import org.springframework.http.HttpEntity
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.web.client.RestTemplate

@Service
class OllamaServiceImpl(
    @Value("\${ollama.model-name:llama3.2}")
    private val modelName: String
) : OllamaService {

    private val logger = LoggerFactory.getLogger(javaClass)
    private val restTemplate = RestTemplate()
    private val ollamaUrl = "http://localhost:11434/api/generate"

    override suspend fun generateResponse(
        prompt: String,
        options: Llama32Options
    ): String {
        try {
            // Prepare headers
            val headers = HttpHeaders()
            headers.contentType = MediaType.APPLICATION_JSON

            // Prepare request body
            val request = mapOf(
                "model" to modelName,
                "prompt" to prompt,
                "stream" to false
            )

            // Create HTTP entity
            val entity = HttpEntity(request, headers)

            logger.info("Sending request to Ollama: $request")

            // Send request and parse response
            val response = restTemplate.postForObject(
                ollamaUrl,
                entity,
                Map::class.java
            )

            logger.info("Received response from Ollama: $response")

            // Extract response
            return response?.get("response")?.toString()
                ?: throw OllamaException("No content in response")

        } catch (e: Exception) {
            logger.error("Failed to generate response from Ollama", e)
            throw OllamaException("Failed to generate response: ${e.message}", e)
        }
    }

    override suspend fun isServiceAvailable(): Boolean {
        return try {
            val response = restTemplate.getForObject("http://localhost:11434/", String::class.java)
            response?.contains("Ollama is running", ignoreCase = true) ?: false
        } catch (e: Exception) {
            logger.warn("Ollama service availability check failed", e)
            false
        }
    }
}