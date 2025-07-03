package com.ppro.interview.billing

import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.ExampleObject
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import mu.KotlinLogging.logger
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/hello")
class HelloWorldController {
    val log = logger {}

    @Operation(summary = "Says hello back to recipient")
    @ApiResponses(
        ApiResponse(
            responseCode = "200", description = "Happy path says hello",
            content = [Content(
                schema = Schema(implementation = HelloResponse::class),
                examples = [ExampleObject(value = """{ "message": "hello", "recipient": "kitten" }""")]
            )]
        )
    )
    @GetMapping("{name}")
    fun hello(@PathVariable("name") name: String): HelloResponse {
        log.info { "Saying hi to $name" }
        return HelloResponse("hello", name)
    }

}

data class HelloResponse(val message: String, val recipient: String)