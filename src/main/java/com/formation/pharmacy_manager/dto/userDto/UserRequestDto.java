package com.formation.pharmacy_manager.dto.userDto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    @NotBlank(message = "Le nom d'utilisateur ne doit pas être vide.")
    @Size(max = 20, message = "Le nom d'utilisateur doit contenir au maximum 20 caractères.")
    private String userName;

    @NotBlank(message = "Le numéro de téléphone est requis.")
    @Size(min = 9, max = 12, message = "Le numéro doit contenir entre 9 et 12 chiffres.")
    private String phoneNumber;

    @NotBlank(message = "L'email est requis.")
    @Email(message = "L'email doit être valide.")
    private String email;

    @NotBlank(message = "Le mot de passe est requis.")
    @Size(min = 12, message = "Le mot de passe doit contenir au moins 12 caractères.")
    private String password;

    @NotBlank(message = "Le rôle est requis.")
    private String role;
}
