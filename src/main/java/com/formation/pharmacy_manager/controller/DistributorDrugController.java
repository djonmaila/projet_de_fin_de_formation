package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.DistributorDrugDto.DisDrugQteDto;
import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugDequestDto;
import com.formation.pharmacy_manager.entities.DistributorDrug;
import com.formation.pharmacy_manager.services.serviceDistributorDrug.DistributorDrugService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/drugline")
@AllArgsConstructor
public class DistributorDrugController {
    private DistributorDrugService distributorDrugService;

    @PostMapping("/create")
    public ResponseEntity<DistributorDrug> create(@RequestBody DistributorDrugDequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(distributorDrugService.create(dto));
    }
}
