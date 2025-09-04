package com.formation.pharmacy_manager.services.servicePatient;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto dto);
}
