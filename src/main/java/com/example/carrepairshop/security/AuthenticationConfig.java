package com.example.carrepairshop.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class AuthenticationConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, "/api/cars").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/mechanics", "/api/mechanics/{id}").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/services", "/api/services/**").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/clients/profile").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/users/profile").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers("/api/reservations", "/api/reservations/**").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers("/api/clients", "/api/clients/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/users", "/api/users/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/cars", "/api/cars/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/mechanics", "/api/mechanics/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/repair-jobs", "/api/repair-jobs/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/services", "/api/services/**").hasAuthority(ADMIN)
                        .requestMatchers( "/auth/**").permitAll()
                        .requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .csrf(AbstractHttpConfigurer::disable)
                .build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    public static final String ADMIN = "ADMIN";
    public static final String USER = "USER";
}
