package com.formation.pharmacy_manager.services.serviceDistributorDrug;

import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugDequestDto;
import com.formation.pharmacy_manager.entities.DistributorDrug;

public interface DistributorDrugService {
    DistributorDrug create(DistributorDrugDequestDto dto);
}
