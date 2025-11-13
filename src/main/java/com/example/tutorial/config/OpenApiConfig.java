package com.example.tutorial.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Spring Boot Tutorial API")
                        .description("Spring Boot教程完整应用示例")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("开发者")
                                .email("developer@example.com")));
    }
}