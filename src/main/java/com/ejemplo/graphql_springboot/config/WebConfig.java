package com.ejemplo.graphql_springboot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebConfig {
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // permite todas las rutas
                        .allowedOrigins("*") // permite cualquier origen (o reemplaza con "https://lucasconstantino.github.io")
                        .allowedMethods("*") // permite GET, POST, PUT, DELETE, etc.
                        .allowedHeaders("*");
            }
        };
    }
}
