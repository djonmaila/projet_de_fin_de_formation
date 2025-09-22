package com.formation.pharmacy_manager.dto.pharmacistDto;

import java.time.LocalDate;
import java.util.Date;

public record PharmacistResponseDto(
        long userId,
        String userName,
        String phoneNumber,
        String email,
        LocalDate creation_date,
        Date update_date
) {
}
