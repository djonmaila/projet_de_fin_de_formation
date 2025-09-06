package com.formation.pharmacy_manager.dto.drugDto;

import java.time.LocalDate;
import java.util.Date;

public record DrugResponseDto(
         long idDrug,
         String drugName,
         String drugDescription,
         LocalDate peremption,
         double price,
         String type,
         Date creation,
         Date update
) {
}
