package com.formation.pharmacy_manager.services.servicePatient;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.entities.Patient;
import com.formation.pharmacy_manager.repository.PatientRepository;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;
    @Override
    public PatientResponseDto createPatient(PatientRequestDto dto) {
        Patient newPatient = dto.toPatient(dto);
        Patient saved = patientRepository.save(newPatient);
        return new PatientResponseDto(
                saved.getUserId(),
                saved.getUserName(),
                saved.getPhoneNumber(),
                saved.getEmail(),
                saved.getAge()
        );
    }

    @Override
    public List<PatientResponseDto> getAllPatient() {
        return patientRepository.findAll().stream().map(
                patient -> new PatientResponseDto(
                        patient.getUserId(),
                        patient.getUserName(),
                        patient.getPhoneNumber(),
                        patient.getEmail(),
                        patient.getAge()
                )).toList();
    }

    @Override
    public PatientResponseDto getPatientById(long id) {
        Patient patient = patientRepository.findById(id).get();
        if (patient == null) throw new RuntimeException("user with id : "+id+" not found");
        return new PatientResponseDto(
                patient.getUserId(),
                patient.getUserName(),
                patient.getPhoneNumber(),
                patient.getEmail(),
                patient.getAge()
        );
    }

    @Override
    public String deleteById(long id) {
        if (existById(id)){
            patientRepository.deleteById(id);
            return "patient deleting successfully";
        }else{
            return "enable to delete this user because he wasn't found";
        }
    }

    @Override
    public boolean existById(long id) {
        return patientRepository.existsById(id);
    }
}
