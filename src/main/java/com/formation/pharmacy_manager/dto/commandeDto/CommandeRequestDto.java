package com.formation.pharmacy_manager.dto.commandeDto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommandeRequestDto {
    private String pseudo;
    private String userName;
}
