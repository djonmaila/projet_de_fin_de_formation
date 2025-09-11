package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
// import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;


@Entity 
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;

    private double totalAmount;

    private LocalDate creationDate;

    @OneToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;
    
}
