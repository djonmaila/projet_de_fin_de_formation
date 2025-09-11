package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long paymentId;

    private double totalAmount;
  
    private String paymentMethod;
  
    private String paymentStatus;

    @OneToOne
    @JoinColumn(name = "id_command")
    private Command command; 

    @OneToOne(mappedBy = "payment")
    private Bill bill;
}
