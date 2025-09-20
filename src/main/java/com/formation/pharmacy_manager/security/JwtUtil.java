package com.formation.pharmacy_manager.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.security.Key;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


@Component
public class JwtUtil {

    private final String SECRET = "ma_clef_secrete_super_longue_qui_doit_faire_au_moins_256bits";
    private final long EXPIRATION_TIME = 1000 * 60 * 15; // 15 minutes

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Générer un token
    public String generateToken(String username, List<String> roles) {
        return Jwts.builder()
                .setSubject(username)
                .claim("roles", roles) // <-- Ajout ici
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }

    // Vérifier la validité
    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token);
            return true;
        } catch (JwtException e) {
            return false;
        }
    }

    public List<String> extractRoles(String token) {
        Claims claims = extractAllClaims(token);
        Object rolesObject = claims.get("roles");

        if (rolesObject instanceof List<?>) {
            return ((List<?>) rolesObject).stream()
                    .map(Object::toString)
                    .collect(Collectors.toList());
        }

        return Collections.emptyList();
    }

    // Récupère le username depuis un JWT
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    // Vérifie si le token est expiré
    public boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    // Récupère la date d’expiration
    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    // Méthode générique pour extraire une info du JWT
    /*
    Un claim est donc une donnée contenue dans le token.

    Les claims sont les informations que tu mets dans un JWT
    */
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // Parse le JWT pour récupérer toutes les infos
    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()//nouveau parserBuilder
                .setSigningKey(key) // Utilise la clé secrète
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
