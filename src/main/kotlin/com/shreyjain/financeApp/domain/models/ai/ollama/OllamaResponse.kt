package com.shreyjain.financeApp.domain.models.ai.ollama

data class OllamaResponse(
    val model: String,
    val createdAt: String,
    val response: String,
    val done: Boolean,
    val context: List<Int>? = null,
    val totalDuration: Long? = null,
    val loadDuration: Long? = null,
    val promptEvalCount: Int? = null,
    val promptEvalDuration: Long? = null,
    val evalCount: Int? = null,
    val evalDuration: Long? = null
)