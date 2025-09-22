package com.formation.pharmacy_manager.dto.userDto;

public record CountStat(
        long patient,
        long pharmacist,
        long distributor,
        long drug,
        long command
) {
}
