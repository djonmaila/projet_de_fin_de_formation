package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.pharmacistDto.PharmacistRequestDto;
import com.formation.pharmacy_manager.dto.pharmacistDto.PharmacistResponseDto;
import com.formation.pharmacy_manager.services.pharmacistService.PharmacistSevice;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pharmacist")
@AllArgsConstructor
public class PharmacistController {

    private PharmacistSevice pharmacistSevice;

    @PostMapping("/create")
    public ResponseEntity<PharmacistResponseDto> postPharmacist(@Valid @RequestBody PharmacistRequestDto requestDto){
        return ResponseEntity.ok(pharmacistSevice.create(requestDto));
    }

    @GetMapping("/")
    public ResponseEntity<List<PharmacistResponseDto>> getAllPharmacist(){
        return ResponseEntity.ok(pharmacistSevice.getAllPharmacist());
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deletePharmacistById(@PathVariable long id){
        return ResponseEntity.status(200).body(pharmacistSevice.deleteById(id));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PharmacistResponseDto> updatePharmacist(@PathVariable long id,@Valid @RequestBody PharmacistRequestDto dto){
        return new ResponseEntity<>(pharmacistSevice.update(id,dto), HttpStatus.OK);
    }
}
