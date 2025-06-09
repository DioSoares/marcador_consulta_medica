package com.medConsult.marcadorconsultas.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API - Marcador de Consultas Médicas",
                version = "1.0.0",
                description = "Documentação da API para gerenciamento de médicos, pacientes e consultas",
                contact = @Contact(
                        name = "Diogenes Soares",
                        email = "diosoaresdev@gmail.com"),
                license = @License(
                        name = "Apache 2.0",
                        url = "http://www.apache.org/licenses/LICENSE-2.0.html")
        ))
public class SwaggerConfig {
}
