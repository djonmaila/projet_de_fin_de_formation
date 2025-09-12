package com.formation.pharmacy_manager.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception générique pour la gestion des erreurs liées aux paiements.
 * Il est recommandé d'utiliser des exceptions plus spécifiques
 * pour un meilleur traitement des erreurs.
 */
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class PaymentException extends RuntimeException {

    public PaymentException(String message) {
        super(message);
    }
}
