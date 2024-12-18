package com.shreyjain.financeApp.services

import com.shreyjain.financeApp.domain.models.ai.ollama.Llama32Options

interface OllamaService {
    /**
     * Generates a response from Llama 3.2 based on the provided prompt
     * @param prompt The input prompt to send to the model
     * @param options Configuration options for Llama 3.2
     * @return The generated response text
     * @throws OllamaException if the request fails
     */
    suspend fun generateResponse(
        prompt: String,
        options: Llama32Options = Llama32Options()
    ): String

    /**
     * Checks if the Ollama service is available and Llama 3.2 is loaded
     * @return true if the service is responding and model is available
     */
    suspend fun isServiceAvailable(): Boolean
}
