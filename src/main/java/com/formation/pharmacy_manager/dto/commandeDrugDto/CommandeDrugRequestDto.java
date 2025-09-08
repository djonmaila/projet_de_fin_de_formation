package com.formation.pharmacy_manager.dto.commandeDrugDto;

import lombok.Getter;

@Getter
public class CommandeDrugRequestDto {
    private String pseudo;
    private String drugName;
    private int quantity;
}
