package com.vecindapp.utils;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "VecindAppi",
                description = "Api para el uso de servicios de VecindApp",
                termsOfService = "Debe aceptar los terminos y condiciones",
                contact = @Contact(name ="VecindApp Support", url = "www.vecindapp.com", email = "vecindapp@gmail.com"),
                version = "1.0"
        )/*,
        security = @SecurityRequirement(
                name = "Security con JWT"
        )*/
)
/*
@SecurityScheme(
        name = "Security con JWT",
        description = "La seguridad del API por medio de un token",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "bearer",
        bearerFormat ="JWT"

)*/
public class SwaggerConfig {
}
