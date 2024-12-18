package com.shreyjain.financeApp.domain.models.ai.ollama

data class OllamaRequest(
    val model: String = "llama2:3.2",
    val prompt: String,
    val stream: Boolean = false,
    val options: Llama32Options = Llama32Options()
)