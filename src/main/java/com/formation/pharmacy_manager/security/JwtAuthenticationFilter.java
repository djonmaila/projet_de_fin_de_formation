package com.formation.pharmacy_manager.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter { // Filtre qui s'exécute une seule fois par requête

    private final JwtUtil jwtUtil; // Outil pour gérer les tokens JWT

    public JwtAuthenticationFilter(JwtUtil jwtUtil) { // Constructeur pour injecter JwtUtil
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException { // Méthode exécutée pour filtrer chaque requête HTTP

        String authHeader = request.getHeader("Authorization"); // Récupère l'en-tête Authorization de la requête
        if (authHeader != null && authHeader.startsWith("Bearer ")) { // Vérifie si l'en-tête commence par "Bearer "
            String jwt = authHeader.substring(7); // Extrait le token en supprimant "Bearer "
            if (jwtUtil.validateToken(jwt)) { // Vérifie si le token est valide
                String username = jwtUtil.extractUsername(jwt); // Extrait le nom d'utilisateur du token
                List<String> roles = jwtUtil.extractRoles(jwt); // Extrait les rôles du token

                List<SimpleGrantedAuthority> authorities = roles.stream() // Transforme les rôles en autorités Spring
                        .map(role -> new SimpleGrantedAuthority("ROLE_" + role)) // Préfixe les rôles avec "ROLE_"
                        .toList();
                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(username, null, authorities); // Crée un objet d'authentification avec l'utilisateur et ses rôles

                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); // Ajoute des détails de la requête à l'objet d'authentification

                SecurityContextHolder.getContext().setAuthentication(authentication); // Enregistre l'authentification dans le contexte de sécurité
            }
        }
        chain.doFilter(request, response); // Continue le traitement de la requête
    }
}
