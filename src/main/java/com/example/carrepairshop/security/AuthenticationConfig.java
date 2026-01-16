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
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/visitor-requests/new").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/services", "/api/services/**").permitAll()
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers("/", "/error", "/csrf", "/swagger-ui.html", "/swagger-ui/**", "/v3/api-docs", "/v3/api-docs/**").permitAll()
                        .requestMatchers("/api/visitor-requests", "/api/visitor-requests/**").hasAuthority(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/cars").hasAnyAuthority(ADMIN, USER, MECHANIC)
                        .requestMatchers(HttpMethod.GET, "/api/mechanics", "/api/mechanics/{id}").hasAnyAuthority(ADMIN, USER, MECHANIC)
                        .requestMatchers(HttpMethod.GET, "/api/clients/profile").hasAnyAuthority(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/mechanics/profile").hasAnyAuthority(ADMIN, MECHANIC)
                        .requestMatchers(HttpMethod.GET, "/api/users/profile").hasAnyAuthority(ADMIN, USER, MECHANIC)
                        .requestMatchers("/api/reservations", "/api/reservations/**").hasAnyAuthority(ADMIN, USER, MECHANIC)
                        .requestMatchers("/api/clients", "/api/clients/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/users", "/api/users/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/cars", "/api/cars/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/mechanics", "/api/mechanics/**").hasAuthority(ADMIN)
                        .requestMatchers("/api/repair-jobs", "/api/repair-jobs/**").hasAuthority(ADMIN)
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
    public static final String MECHANIC = "MECHANIC";
}
