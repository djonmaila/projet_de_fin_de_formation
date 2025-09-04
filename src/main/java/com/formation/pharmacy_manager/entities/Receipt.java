package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Receipt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long receiptId;

    @NotBlank
    private double totalAmount;

    @NotNull
    private LocalDate creation_date;

    @OneToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;
}
