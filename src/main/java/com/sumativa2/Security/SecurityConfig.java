package com.sumativa2.Security;


import org.springframework.http.HttpMethod;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())   
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/login", "/registro").permitAll()   
                .requestMatchers(HttpMethod.GET, "/usuarios/**").permitAll()   
                .requestMatchers(HttpMethod.PUT, "/usuarios").authenticated() 
                 .requestMatchers(HttpMethod.PUT, "/usuarios/**").permitAll()      

                .requestMatchers(HttpMethod.POST, "/usuarios/**").permitAll()      
                .requestMatchers(HttpMethod.POST, "/foro/**/comentarios").permitAll()   
                .requestMatchers(HttpMethod.POST, "/foro/**").permitAll()   
                .requestMatchers(HttpMethod.POST, "/usuarios/foro/comentarios").permitAll()   
                .requestMatchers(HttpMethod.GET, "/usuarios/foro").permitAll()   
                .requestMatchers(HttpMethod.DELETE, "/usuarios/**").permitAll()   

                .anyRequest().authenticated()   
            
                )
                .formLogin(form -> form.disable()
        
            )
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)   
            );
        
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();   
    }
}