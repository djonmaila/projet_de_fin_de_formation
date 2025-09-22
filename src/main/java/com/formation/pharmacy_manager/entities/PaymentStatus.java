package com.formation.pharmacy_manager.entities;

public enum PaymentStatus {
    PENDING, // En attente de traitement
    FINISHED, // Le paiement a été effectué avec succès
    FAILED, // Le paiement a échoué
    REFUNDED // Le paiement a été remboursé
}
