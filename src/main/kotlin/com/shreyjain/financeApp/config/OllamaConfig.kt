package com.shreyjain.financeApp.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.beans.factory.annotation.Value

@Configuration
class OllamaConfig {
    @Bean
    fun ollamaWebClient(): WebClient {
        return WebClient.builder()
            .baseUrl("http://localhost:11434")
            .defaultHeader("Content-Type", "application/json")
            .build()
    }
}