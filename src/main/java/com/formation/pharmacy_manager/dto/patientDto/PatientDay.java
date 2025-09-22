package com.formation.pharmacy_manager.dto.patientDto;

import java.time.LocalDate;

public record PatientDay(
        long patient,
        LocalDate date
) {
}
