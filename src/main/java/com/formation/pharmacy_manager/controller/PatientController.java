package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.services.servicePatient.PatientService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> postPatient(@RequestBody PatientRequestDto requestDto){
        return ResponseEntity.ok(patientService.createPatient(requestDto));
    }
}
