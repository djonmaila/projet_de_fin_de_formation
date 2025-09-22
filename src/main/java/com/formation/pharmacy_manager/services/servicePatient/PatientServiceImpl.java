package com.formation.pharmacy_manager.services.servicePatient;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.entities.Command;
import com.formation.pharmacy_manager.entities.Patient;
import com.formation.pharmacy_manager.entities.Role;
import com.formation.pharmacy_manager.enumEntities.Type;
import com.formation.pharmacy_manager.repository.PatientRepository;
import com.formation.pharmacy_manager.repository.RoleRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Service
public class PatientServiceImpl implements PatientService{
    private PatientRepository patientRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    @Override
    public PatientResponseDto createPatient(PatientRequestDto requestDto) {
        Patient patient = new Patient();
        patient.setUserName(requestDto.getUserName());
        patient.setEmail(requestDto.getEmail());
        patient.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        patient.setAge(requestDto.getAge());
        patient.setPhoneNumber(requestDto.getPhoneNumber());
        patient.setCreation_date(LocalDate.now());
        patient.setUpdate_date(new Date());

        Role role = roleRepository.getByType(Type.valueOf(requestDto.getRole()));
        patient.getRoles().add(role);
        Patient saved = patientRepository.save(patient);
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

    @Override
    public PatientResponseDto updatePatient(long id, PatientRequestDto requestDto) {
        Patient patient = patientRepository.findById(id).get();
        if (patient ==null) throw new RuntimeException("updating impossible because this user doesn't exist");
        patient.setUserName(requestDto.getUserName());
        patient.setEmail(requestDto.getEmail());
        patient.setPassword(passwordEncoder.encode(requestDto.getPassword()));
        patient.setAge(requestDto.getAge());
        patient.setPhoneNumber(requestDto.getPhoneNumber());
        patient.setUpdate_date(new Date());

        Patient pa = patientRepository.save(patient);
        return new PatientResponseDto(
                pa.getUserId(),
                pa.getUserName(),
                pa.getPhoneNumber(),
                pa.getEmail(),
                pa.getAge()
        );
    }
}
