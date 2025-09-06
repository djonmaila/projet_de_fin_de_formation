package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.commandeDto.CommandeRequestDto;
import com.formation.pharmacy_manager.dto.commandeDto.CommandeResponseDto;
import com.formation.pharmacy_manager.dto.drugDto.DrugResponseDto;
import com.formation.pharmacy_manager.services.commandeService.CommandService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/command")
@AllArgsConstructor
public class CommandeController {
    private CommandService commandService;

    @PostMapping("/create")
    public ResponseEntity<CommandeResponseDto> create(@RequestBody CommandeRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(commandService.createCommande(dto));
    }

    @GetMapping("/listDrug/{pseudo}")
    public ResponseEntity<List<DrugResponseDto>> getDrugContentToCommande(@PathVariable String pseudo){
        return ResponseEntity.status(HttpStatus.OK).body(commandService.getListDrugToCommand(pseudo));
    }
}
