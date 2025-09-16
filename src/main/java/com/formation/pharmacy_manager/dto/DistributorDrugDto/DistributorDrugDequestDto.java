package com.formation.pharmacy_manager.dto.DistributorDrugDto;

import com.formation.pharmacy_manager.entities.DistributorDrug;
import lombok.Getter;

import java.util.Date;

@Getter
public class DistributorDrugDequestDto {
    private String userName;
    private String drugName;
    private int qte;
}
