package com.shreyjain.financeApp

import com.shreyjain.financeApp.domain.dto.UserDto
import com.shreyjain.financeApp.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath
import com.fasterxml.jackson.databind.ObjectMapper

@SpringBootTest
@AutoConfigureMockMvc
class UserIntegrationTest {

	@Autowired
	private lateinit var mockMvc: MockMvc

	@Autowired
	private lateinit var userRepository: UserRepository

	@Autowired
	private lateinit var objectMapper: ObjectMapper

	@AfterEach
	fun cleanup() {
		userRepository.deleteAll()
	}

	@Test
	fun `should create user when valid data is provided`() {
		val userDto = UserDto(
			username = "testuser",
			email = "test@test.com",
			password = "password123"
		)

		mockMvc.perform(post("/api/auth/user/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isOk)
			.andExpect(jsonPath("$.username").value("testuser"))
			.andExpect(jsonPath("$.email").value("test@test.com"))
	}

	@Test
	fun `should return error when invalid data is provided`() {
		val userDto = UserDto(
			username = "",
			email = "invalid",
			password = "short"
		)

		mockMvc.perform(post("/api/auth/user/register")
			.contentType(MediaType.APPLICATION_JSON)
			.content(objectMapper.writeValueAsString(userDto)))
			.andExpect(status().isBadRequest)
			.andExpect(jsonPath("$.errors").isArray)
	}
}