package com.formation.pharmacy_manager.dto.patientDto;

import com.formation.pharmacy_manager.dto.userDto.UserRequestDto;
import com.formation.pharmacy_manager.entities.Patient;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Date;

@Getter
public class PatientRequestDto extends UserRequestDto {

    private int age;

    public PatientRequestDto(String userName, String phoneNumber, String email, String password,int age,String role) {
        super(userName, phoneNumber, email, password,role);
        this.age = age;
    }
}
