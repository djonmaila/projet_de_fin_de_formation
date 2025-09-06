package com.formation.pharmacy_manager.dto.distributorDto;


import java.time.LocalDate;
import java.util.Date;

public record DistributorResponseDto (
     long userId,
     String userName,
     String phoneNumber,
     String email,
     String distributorReference,
     LocalDate creation_date,
     Date update_date)
{
}
