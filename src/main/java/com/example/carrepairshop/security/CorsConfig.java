package com.example.carrepairshop.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Collections;
import org.springframework.lang.NonNull;

@Configuration
public class CorsConfig {
    @Bean
    CorsConfigurationSource corsConfigurationSource(@Value("${app.cors.allowed-origins:*}") String allowedOrigins) {
        CorsConfiguration configuration = new CorsConfiguration();
        if (!"*".equals(allowedOrigins)) {
            configuration.setAllowCredentials(true);
            configuration.setAllowedOrigins(Collections.singletonList(allowedOrigins));
        } else {
            configuration.setAllowCredentials(false);
            configuration.setAllowedOriginPatterns(Collections.singletonList("*"));
        }
        configuration.addAllowedMethod("*");
        configuration.addAllowedHeader("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    @Bean
    public WebMvcConfigurer corsConfigurer(@Value("${app.cors.allowed-origins:*}") String allowedOrigins) {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(@NonNull CorsRegistry registry) {
                if (!"*".equals(allowedOrigins)) {
                    registry.addMapping("/**")
                            .allowedOrigins(allowedOrigins)
                            .allowedMethods("*")
                            .allowedHeaders("*")
                            .allowCredentials(true);
                } else {
                    registry.addMapping("/**")
                            .allowedOriginPatterns("*")
                            .allowedMethods("*")
                            .allowedHeaders("*")
                            .allowCredentials(false);
                }
            }
        };
    }
}
