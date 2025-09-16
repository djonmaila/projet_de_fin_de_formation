package com.formation.pharmacy_manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class PharmacyManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(PharmacyManagerApplication.class, args);
	}

    @Bean // On expose le PasswordEncoder comme un bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // BCrypt = encodage sécurisé des mots de passe
    }

}
