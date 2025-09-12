package com.formation.pharmacy_manager.dto.commandeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TotalMoneyPerCommand {
    private String pseudo;
    private double priceTotal;
}
