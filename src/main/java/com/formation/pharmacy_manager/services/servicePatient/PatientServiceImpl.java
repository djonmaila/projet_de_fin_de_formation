package com.formation.pharmacy_manager.services.servicePatient;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.entities.Patient;
import com.formation.pharmacy_manager.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;
    private PatientRequestDto requestDto;
    @Override
    public PatientResponseDto createPatient(PatientRequestDto dto) {
        Patient newPatient = requestDto.toPatient(dto);
        Patient saved = patientRepository.save(newPatient);
        return new PatientResponseDto(
                saved.getUserId(),
                saved.getUserName(),
                saved.getPhoneNumber(),
                saved.getEmail()
        );
    }
}
