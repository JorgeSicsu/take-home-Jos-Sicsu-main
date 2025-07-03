package com.ppro.interview.billing

import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(HelloWorldController::class)
internal class HelloWorldControllerTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun sayHiToKitten() {
        mockMvc.get("/v1/hello/kitten").andExpect {
            status().isOk
            content { json("""{ "message": "hello", "recipient": "kitten" }""", strict = true) }
        }
    }

    @Test
    fun returnsNotFoundIfNoNameProvided() {
        mockMvc.get("/v1/hello/").andExpect { status().isNotFound }
    }
}