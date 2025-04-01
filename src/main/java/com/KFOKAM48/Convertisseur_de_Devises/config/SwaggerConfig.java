package com.KFOKAM48.Convertisseur_de_Devises.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Conversion de Devises")
                        .version("1.0")
                        .description("API pour convertir des montants entre différentes devises"));
    }
}