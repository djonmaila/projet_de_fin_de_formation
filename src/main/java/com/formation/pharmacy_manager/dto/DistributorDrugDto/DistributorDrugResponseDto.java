package com.formation.pharmacy_manager.dto.DistributorDrugDto;


import java.util.Date;

public record DistributorDrugResponseDto(
        long id,
        String userDis,
        String drugName,
        int quantity,
        Date creation,
        Date update
) {
}
  