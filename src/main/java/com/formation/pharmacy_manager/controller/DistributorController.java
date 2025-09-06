package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.distributorDto.DistributorRequestDto;
import com.formation.pharmacy_manager.dto.distributorDto.DistributorResponseDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.dto.patientDto.PatientResponseDto;
import com.formation.pharmacy_manager.entities.Drug;
import com.formation.pharmacy_manager.services.serviceDistributor.DistributorService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/distributor")
@AllArgsConstructor
public class DistributorController {
    private DistributorService distributorService;

    @PostMapping("/create")
    public ResponseEntity<DistributorResponseDto> create(@RequestBody DistributorRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(distributorService.create(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<DistributorResponseDto>> getAllDistributor(){
        return ResponseEntity.ok(distributorService.getAllDistributor());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistributorResponseDto> getById(@PathVariable long id){
        return ResponseEntity.ok(distributorService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return ResponseEntity.status(200).body(distributorService.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DistributorResponseDto> updateDistributor(@PathVariable long id , @RequestBody DistributorRequestDto dto){
        return  ResponseEntity.status(HttpStatus.OK).body(distributorService.updateDistributor(id,dto));
    }

    @GetMapping("/allDrug/{name}")
    public ResponseEntity<List<DrugResponseDto>> getAllDrugFromDistributor(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(distributorService.getDrugFromDistributor(name));
    }

    @GetMapping("/findByEmail/{email}")
    public ResponseEntity<DistributorResponseDto> getByEmail(@PathVariable String email){
        return ResponseEntity.status(HttpStatus.OK).body(distributorService.findDistinctByEmail(email));
    }
}
