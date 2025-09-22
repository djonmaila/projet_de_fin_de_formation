package com.formation.pharmacy_manager.dto.commandeDto;

import java.time.LocalDate;

public record CommandeResponseDto(long idcom, String pseudo, String userName, LocalDate date) {
}
 