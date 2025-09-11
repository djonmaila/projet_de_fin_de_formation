package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class CommandDrug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long commandDrugId;

    private int quantity;

    private String userDis;

    private LocalDate date;

    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "id_command")
    private Command command;

    @ManyToOne
    @JoinColumn(name = "id_drug")
    private Drug drug;
}
