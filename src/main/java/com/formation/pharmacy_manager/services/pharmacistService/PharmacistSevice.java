package com.formation.pharmacy_manager.services.pharmacistService;

import com.formation.pharmacy_manager.dto.pharmacistDto.PharmacistRequestDto;
import com.formation.pharmacy_manager.dto.pharmacistDto.PharmacistResponseDto;

import java.util.List;

public interface PharmacistSevice {
    PharmacistResponseDto create(PharmacistRequestDto dto);
    List<PharmacistResponseDto> getAllPharmacist();
    String deleteById(long id);
    PharmacistResponseDto update(long id,PharmacistRequestDto dto);
}
