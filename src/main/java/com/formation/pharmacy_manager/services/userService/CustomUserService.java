package com.formation.pharmacy_manager.services.userService;

import com.formation.pharmacy_manager.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service // Déclare cette classe comme un service Spring (bean géré par Spring)
@AllArgsConstructor
public class CustomUserService implements UserDetailsService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) {
        // Cette méthode est appelée par Spring Security pour chercher un utilisateur
        // Ici on simule avec un utilisateur "testUser" stocké en mémoire
        com.formation.pharmacy_manager.entities.User user = userRepository.findDistinctByUserName(username);

        if (user!=null) {
            return User.builder()
                    .username(user.getUserName()) // nom d’utilisateur
                    .password(user.getPassword()) // mot de passe encodé (BCrypt)
                    .roles(user.getRoles().stream().map(role -> role.getType().name()).toArray(String[]::new)) // rôle de l’utilisateur
                    .build(); // retourne un UserDetails
        }

        // Si l'utilisateur n'existe pas → exception
        throw new RuntimeException("Utilisateur introuvable: " + username);
    }
}
