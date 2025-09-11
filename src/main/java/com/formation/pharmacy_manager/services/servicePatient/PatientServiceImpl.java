package com.formation.pharmacy_manager.services.servicePatient;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.entities.Patient;
import com.formation.pharmacy_manager.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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
        if (patient == null) throw new RuntimeException("user avec l'id : "+id+" introuvable");
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
            return "patient supprimer avec succès";
        }else{
            return "suppression échoué";
        }
    }

    @Override
    public boolean existById(long id) {
        return patientRepository.existsById(id);
    }
}
