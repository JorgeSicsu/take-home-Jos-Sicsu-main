package com.example.take_home_java_billing;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/hello")
@Slf4j
public class HelloWorldController {
    @Operation(description = "Says hello back to recipient",
            responses = {@ApiResponse(responseCode = "200", description = "Happy path says hello",
                    content = {@Content(schema = @Schema(implementation = HelloResponse.class),
                            examples = {@ExampleObject(value = "{ \"message\": \"hello\", \"recipient\": \"kitten\" }")}
                    )})}
    )
    @GetMapping("{name}")
    public HelloResponse hello(@PathVariable("name") String name) {
        log.info("Saying hi to {}", name);
        return new HelloResponse("hello", name);
    }

}