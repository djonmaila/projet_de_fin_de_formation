package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.DistributorDrugDto.DisDrugQteDto;
import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugDequestDto;
import com.formation.pharmacy_manager.dto.DistributorDrugDto.DistributorDrugResponseDto;
import com.formation.pharmacy_manager.entities.DistributorDrug;
import com.formation.pharmacy_manager.services.serviceDistributorDrug.DistributorDrugService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drugline")
@AllArgsConstructor
public class DistributorDrugController {
    private DistributorDrugService distributorDrugService;

    @PostMapping("/create")
    public ResponseEntity<DistributorDrugResponseDto> create(@RequestBody DistributorDrugDequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(distributorDrugService.create(dto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DistributorDrugResponseDto> getById(@PathVariable long id){
        return new ResponseEntity<>(distributorDrugService.getById(id),HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<DistributorDrugResponseDto>> getAllLineShark(){
        return new ResponseEntity<>(distributorDrugService.getAllLineDisDrug(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    ResponseEntity<String> deleteById(@PathVariable long id){
        return new ResponseEntity<>(distributorDrugService.deleteById(id),HttpStatus.OK);
    }

    @GetMapping("/{userName}/{drugName}")
    public ResponseEntity<DistributorDrugResponseDto> getByUserDisAndNameDrug(@PathVariable String userName,@PathVariable String drugName){
        return new ResponseEntity<>(distributorDrugService.getByUserNameAndDrugName(drugName,userName),HttpStatus.OK);
    }
}
