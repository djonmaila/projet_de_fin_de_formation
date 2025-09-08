package com.formation.pharmacy_manager.dto.commandeDrugDto;

import java.time.LocalDate;
import java.time.LocalTime;

public record CommandeDrugResponseDto(
         long id,
         String pseudo,
         String drugName,
         int quantity,
         LocalDate date,
         LocalTime time
) {
}
