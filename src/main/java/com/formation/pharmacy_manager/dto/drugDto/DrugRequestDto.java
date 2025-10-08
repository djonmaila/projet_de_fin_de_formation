package com.formation.pharmacy_manager.dto.drugDto;

import com.formation.pharmacy_manager.entities.Drug;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@AllArgsConstructor
public class DrugRequestDto {
    private String  drugName;
    private String drugDescription;
    private LocalDate peremption;
    private double price;
    private String type;
}
