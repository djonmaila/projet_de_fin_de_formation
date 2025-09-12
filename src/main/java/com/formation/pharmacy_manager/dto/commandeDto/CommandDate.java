package com.formation.pharmacy_manager.dto.commandeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class CommandDate {
    private LocalDate date;
    private long totalCommand;
}
