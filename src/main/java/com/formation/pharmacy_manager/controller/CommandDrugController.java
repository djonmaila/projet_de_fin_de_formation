package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.commandeDrugDto.CommandeDrugRequestDto;
import com.formation.pharmacy_manager.dto.commandeDrugDto.CommandeDrugResponseDto;
import com.formation.pharmacy_manager.services.commandDrugService.CommandDrugService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commandDrug")
@AllArgsConstructor
public class CommandDrugController {
    private CommandDrugService commandDrugService;

    @PostMapping("/create")
    public ResponseEntity<CommandeDrugResponseDto> createCommandDrug(@RequestBody CommandeDrugRequestDto dto){
        return ResponseEntity.status(HttpStatus.OK).body(commandDrugService.create(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<CommandeDrugResponseDto>> getAllCommandeDrug(){
        return ResponseEntity.status(HttpStatus.OK).body(commandDrugService.getAllCommandDrug());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CommandeDrugResponseDto> getCommandDugById(@PathVariable long id){
        return ResponseEntity.status(HttpStatus.OK).body(commandDrugService.getById(id));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteById(@PathVariable long id){
        return new ResponseEntity<>(commandDrugService.deleteById(id),HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommandeDrugResponseDto> updateCommandDrug(@PathVariable long id ,@RequestBody CommandeDrugRequestDto dto){
        return new ResponseEntity<>(commandDrugService.update(id,dto),HttpStatus.OK);
    }
}
