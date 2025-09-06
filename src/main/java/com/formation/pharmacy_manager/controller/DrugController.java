package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.drugDto.DrugRequestDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.services.drugService.DrugService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/drug")
@AllArgsConstructor
public class DrugController {
    private DrugService drugService;

    @PostMapping("/create")
    public ResponseEntity<DrugResponseDto> createDrug(@RequestBody DrugRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(drugService.createDrug(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<DrugResponseDto>> getAllDistributor(){
        return ResponseEntity.ok(drugService.getAllDrug());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DrugResponseDto> getById(@PathVariable long id){
        return ResponseEntity.ok(drugService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return ResponseEntity.status(200).body(drugService.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<DrugResponseDto> updateDistributor(@PathVariable long id , @RequestBody DrugRequestDto dto){
        return  ResponseEntity.status(HttpStatus.OK).body(drugService.updateDrug(id,dto));
    }
}
