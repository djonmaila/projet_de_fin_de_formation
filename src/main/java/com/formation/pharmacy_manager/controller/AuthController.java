package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.userDto.UserConnectDto;
import com.formation.pharmacy_manager.dto.userDto.UserConnectResponse;
import com.formation.pharmacy_manager.security.JwtUtil;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager; // Gère l’authentification
    private final JwtUtil jwtUtil; // Utilitaire JWT



    @PostMapping("/login") // Endpoint POST /login
    public ResponseEntity<?> login(@RequestBody UserConnectDto request) {
        // Crée un token UsernamePassword pour vérifier les identifiants
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
        );

        // Si authentification réussie
        if (authentication.isAuthenticated()) {
            List<String> roles = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .map(role -> role.replace("ROLE_", "")) // Nettoie le prefix "ROLE_" si nécessaire
                    .toList();

            // Génère un JWT pour l’utilisateur
            String token = jwtUtil.generateToken(request.getUsername(),roles);

            // Retourne le token + username dans une réponse JSON
            return ResponseEntity.ok(new UserConnectResponse(token, authentication.getName()));
        } else {
            // Sinon renvoie 401 Unauthorized
            return ResponseEntity.status(401).body("Invalid credentials");
        }
    }
}