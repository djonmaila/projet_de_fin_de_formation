package com.formation.pharmacy_manager.dto.commandeDrugDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class CommandeDrugRequestDto {
    private String pseudo;
    private String drugName;
    private int quantity;
}

