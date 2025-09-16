package com.formation.pharmacy_manager.configurations;

import com.formation.pharmacy_manager.security.JwtAuthenticationFilter;
import com.formation.pharmacy_manager.services.userService.CustomUserService;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration // Classe de configuration Spring
@AllArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter; // Notre filtre JWT
    private final CustomUserService userDetailsService; // Service utilisateur
    private final PasswordEncoder passwordEncoder; // Pour encoder les mots de passe



    @Bean // Définit la configuration de sécurité principale
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(csrf -> csrf.disable()) // Désactive CSRF car on utilise JWT (pas de session côté serveur)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login").permitAll() // "/login" est public
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers("/api/distributor/**").hasAnyRole("DISTRIBUTOR","ADMIN")
                        .requestMatchers("/api/command/**").hasAnyRole("PATIENT","ADMIN")
                        .requestMatchers("/api/patient/**").hasAnyRole("PATIENT","ADMIN")
                        .anyRequest().authenticated() // toutes les autres requêtes nécessitent un token valide
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                // JWT = stateless (pas de session, chaque requête doit avoir un token)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                // Ajoute notre filtre JWT avant le filtre par défaut d’authentification
                .build();
    }

    @Bean // Définit le fournisseur d’authentification
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService); // Utilise notre service utilisateur
        authProvider.setPasswordEncoder(passwordEncoder); // Vérifie les mots de passe encodés
        return authProvider;
    }

    @Bean // Fournit l'AuthenticationManager (utilisé dans le contrôleur)
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager(); // Spring gère la création de l’AuthenticationManager
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList("bearerAuth"))
                .components(new Components()
                        .addSecuritySchemes("bearerAuth", new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")));
    }
}
