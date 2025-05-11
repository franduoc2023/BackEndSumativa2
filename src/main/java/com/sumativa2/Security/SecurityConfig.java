package com.sumativa2.Security;


import java.util.List;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
@ConditionalOnProperty(name = "spring.security.enabled", havingValue = "true", matchIfMissing = false)
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .cors(cors -> {})
            .authorizeHttpRequests(auth -> auth
            .requestMatchers(HttpMethod.GET, "/usuarios/verificar").permitAll()
            .requestMatchers(HttpMethod.GET, "/usuarios/foro").permitAll()
            .requestMatchers(HttpMethod.POST, "/usuarios/foro").permitAll()

                .requestMatchers(HttpMethod.OPTIONS, "/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios/login").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios/registro").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.PUT, "/usuarios").authenticated()
                .requestMatchers(HttpMethod.PUT, "/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/foro/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/usuarios/foro").permitAll()
                .requestMatchers(HttpMethod.POST, "/usuarios/foro/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form.disable())
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
            );
    
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  
    }


    @Bean
public CorsConfigurationSource corsConfigurationSource() {
    CorsConfiguration config = new CorsConfiguration();
    config.setAllowedOrigins(List.of("http://localhost:4200"));
    config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
    config.setAllowCredentials(true);
    config.setAllowedHeaders(List.of("*"));
    config.setAllowCredentials(true);
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", config);
    return source;
}
}
