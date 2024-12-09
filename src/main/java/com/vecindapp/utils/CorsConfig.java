package com.vecindapp.utils;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer{

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica CORS a todas las rutas
                .allowedOrigins("http://localhost:4200", "http://localhost:8080")  // Origen permitido
                .allowedMethods("*")  // Métodos HTTP permitidos
                .allowedHeaders("Authorization", "Content-Type")// Permitir todos los encabezados
                .allowCredentials(true);  // Permitir cookies si es necesario
    }


}
