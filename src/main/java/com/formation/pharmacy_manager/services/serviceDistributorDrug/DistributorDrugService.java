package com.formation.pharmacy_manager.services.serviceDistributorDrug;

import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugDequestDto;
import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugResponseDto;
import com.formation.pharmacy_manager.entities.DistributorDrug;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DistributorDrugService {
    DistributorDrugResponseDto create(DistributorDrugDequestDto dto);
    DistributorDrugResponseDto getByUserNameAndDrugName(String drugName, String userName);
    String deleteById(long id);
    boolean existById(long id);
    DistributorDrugResponseDto getById(long id);
    List<DistributorDrugResponseDto> getAllLineDisDrug();
}
