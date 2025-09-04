package com.formation.pharmacy_manager.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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

    @NotBlank
    private int quantity;

    @NotNull
    private LocalDate date;

    @NotNull
    private LocalTime time;
    @ManyToOne
    @JoinColumn(name = "id_command")
    private Command command;

    @ManyToOne
    @JoinColumn(name = "id_drug")
    private Drug drug;
}
