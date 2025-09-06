package com.formation.pharmacy_manager.services.drugService;

import com.formation.pharmacy_manager.dto.drugDto.DrugRequestDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;

import java.util.List;

public interface DrugService {
    DrugResponseDto createDrug(DrugRequestDto dto);
    DrugResponseDto getById(long id);
    List<DrugResponseDto> getAllDrug();
    String deleteById(long id);
    boolean existById(long id);
    DrugResponseDto updateDrug(long id , DrugRequestDto dto);
}
