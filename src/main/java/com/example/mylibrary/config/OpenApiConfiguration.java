package com.example.mylibrary.config;

import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "My Library API",
        version = "1.0",
        description = "Documentation of My Library API",
        contact = @Contact(
            name = "My Library",
            email = "g.92oliveira@gmail.com"
        )
    )
)
public class OpenApiConfiguration {
    
}
