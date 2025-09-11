package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import lombok.Data;

import java.time.LocalDate;


@Entity 
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long billId;

    private double totalAmount;

    private LocalDate creation_date;

    @OneToOne
    @JoinColumn(name = "id_payment")
    private Payment payment;
}
