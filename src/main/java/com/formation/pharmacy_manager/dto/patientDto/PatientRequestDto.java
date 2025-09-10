package com.formation.pharmacy_manager.dto.patientDto;

import com.formation.pharmacy_manager.dto.userDto.UserRequestDto;
import com.formation.pharmacy_manager.entities.Patient;
// import com.formation.pharmacy_manager.entities.Role;
// import com.formation.pharmacy_manager.enumEntities.Type;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class PatientRequestDto extends UserRequestDto {


    @NotBlank
    private int age;

    public PatientRequestDto(String userName, String phoneNumber, String email, String password,int age) {
        super(userName, phoneNumber, email, password);
        this.age = age;
    }


    public Patient toPatient(PatientRequestDto requestDto){
        Patient patient = new Patient();
        patient.setUserName(requestDto.getUserName());
        patient.setEmail(requestDto.getEmail());
        patient.setPassword(requestDto.getPassword());
        patient.setAge(requestDto.getAge());
        patient.setPhoneNumber(requestDto.getPhoneNumber());
        patient.setCreation_date(LocalDate.now());
        patient.setUpdate_date(new Date());

        return patient;
    }
}
