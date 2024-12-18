package com.shreyjain.financeApp.domain.models.ai.ollama

data class Llama32Options(
    val temperature: Float = 0.7f,
    val numPredict: Int = 128,
    val topK: Int = 40,
    val topP: Float = 0.9f,
    val repeatPenalty: Float = 1.1f,
    val presencePenalty: Float = 0.0f,
    val frequencyPenalty: Float = 0.0f,
    val mirostat: Int = 0,
    val mirostatTau: Float = 5.0f,
    val mirostatEta: Float = 0.1f,
    val seed: Long? = null,
    val numCtx: Int = 4096,
    val numBatch: Int = 512,
    val numGqa: Int = 1,
    val numGpu: Int = 1,
    val mainGpu: Int = 0,
    val lowVram: Boolean = false,
    val f16Kv: Boolean = true,
    val logitsAll: Boolean = false,
    val vocabOnly: Boolean = false,
    val stop: List<String> = emptyList()
)