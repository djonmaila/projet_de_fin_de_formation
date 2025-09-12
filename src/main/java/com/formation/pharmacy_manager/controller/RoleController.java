package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.roleDto.RoleRequestDto;
import com.formation.pharmacy_manager.dto.roleDto.RoleResponseDto;
import com.formation.pharmacy_manager.services.roleService.RoleService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
@AllArgsConstructor
public class RoleController {
    private RoleService roleService;

    @PostMapping("/create")
    public ResponseEntity<RoleResponseDto> createRole(@RequestBody RoleRequestDto dto){
        return new ResponseEntity<>(roleService.create(dto), HttpStatus.OK);
    }

    @GetMapping("/")
    public ResponseEntity<List<RoleResponseDto>> getAllRoles(){
        return new ResponseEntity<>(roleService.getAllRoles(),HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return new ResponseEntity<>(roleService.deleteById(id),HttpStatus.OK);
    }

    @GetMapping("/exist/{id}")
    public ResponseEntity<Boolean> existById(@PathVariable long id){
        return new ResponseEntity<>(roleService.existById(id),HttpStatus.OK);
    }
}
