package com.projeto.gamificacao.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("API Gamificacao EAD")
                .version("1.0")
                .description("Gamificacao para Engajamento de Educacao Continuada")
                .contact(new Contact().name("Projeto Pratica 5")));
    }
}
