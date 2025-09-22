package com.formation.pharmacy_manager.services.servicePatient;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;

import java.util.List;

public interface PatientService {
    PatientResponseDto createPatient(PatientRequestDto dto);
    List<PatientResponseDto> getAllPatient();
    PatientResponseDto getPatientById(long id);
    String deleteById(long id);
    boolean existById(long id);
    PatientResponseDto updatePatient(long id,PatientRequestDto dto);
}
