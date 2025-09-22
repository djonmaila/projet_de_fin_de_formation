package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.patientDto.PatientRequestDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.services.servicePatient.PatientService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patient")
@AllArgsConstructor
public class PatientController {
    private PatientService patientService;

    @PostMapping("/create")
    public ResponseEntity<PatientResponseDto> postPatient(@Valid @RequestBody PatientRequestDto requestDto){
        return ResponseEntity.ok(patientService.createPatient(requestDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<PatientResponseDto>> getAllPatient(){
        return ResponseEntity.ok(patientService.getAllPatient());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDto> getPatientById(@PathVariable long id){
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePatientById(@PathVariable long id){
        return ResponseEntity.status(200).body(patientService.deleteById(id));
    }

    @PutMapping("/update{id}")
    public ResponseEntity<PatientResponseDto> updatePatient(@PathVariable long id,@Valid @RequestBody PatientRequestDto dto){
        return new ResponseEntity<>(patientService.updatePatient(id,dto), HttpStatus.OK);
    }
}
