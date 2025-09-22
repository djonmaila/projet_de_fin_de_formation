package com.formation.pharmacy_manager.controller;

import com.formation.pharmacy_manager.dto.commandeDto.*;
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
    public ResponseEntity<CommandeResponseDto> create(@RequestBody CommandeRequestDto dto) {
        return ResponseEntity.status(HttpStatus.OK).body(commandService.createCommande(dto));
    }

    @GetMapping("/")
    public ResponseEntity<List<CommandeResponseDto>> getAllCommand() {
        return new ResponseEntity<>(commandService.getListCommand(), HttpStatus.OK);
    }

    @GetMapping("/listDrug/{pseudo}")
    public ResponseEntity<List<DrugResponseDto>> getDrugContentToCommande(@PathVariable String pseudo) {
        return ResponseEntity.status(HttpStatus.OK).body(commandService.getListDrugToCommand(pseudo));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCommandById(@PathVariable long id) {
        return new ResponseEntity<>(commandService.deleteById(id), HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<CommandeResponseDto> updateCommande(@PathVariable long id, @RequestBody CommandeRequestDto dto) {
        return new ResponseEntity<>(commandService.updateCommande(id, dto), HttpStatus.OK);
    }

    @GetMapping("/commandDate")
    public ResponseEntity<List<CommandDate>> getTotalCommandPassPerDate() {
        return new ResponseEntity<>(commandService.totalCommandPerDate(), HttpStatus.OK);
    }

    @GetMapping("/commandDate/{userName}")
    public ResponseEntity<List<CommandDate>> getTotalCommandPassPerDate(@PathVariable String userName) {
        return new ResponseEntity<>(commandService.totalCommandPerDateForUser(userName), HttpStatus.OK);
    }

    @GetMapping("/TotalMoneyParCommand")
    public ResponseEntity<List<TotalMoneyPerCommand>> totalRevenuCommand() {
        return new ResponseEntity<>(commandService.totalRevenuCommand(), HttpStatus.OK);
    }
    @GetMapping("/TotalMoneyParCommand/{userName}")
    public ResponseEntity<List<TotalMoneyPerCommand>> totalRevenuCommand(@PathVariable String userName) {
        return new ResponseEntity<>(commandService.totalRevenuCommandForUser(userName), HttpStatus.OK);
    }

    @GetMapping("/TotalQuantityDrugInCommand")
    public ResponseEntity<List<TotalQuantityForDrugCommand>> totalQuantityDrugInCommandDrug() {
        return new ResponseEntity<>(commandService.totalQuantityDrugInCommandDrug(), HttpStatus.OK);
    }

    @GetMapping("/TotalQuantityDrugInCommand/{userName}")
    public ResponseEntity<List<TotalQuantityForDrugCommand>> totalQuantityDrugInCommandDrug(@PathVariable String userName) {
        return new ResponseEntity<>(commandService.totalQuantityDrugInCommandDrugForUser(userName), HttpStatus.OK);
    }

    @GetMapping("/totalDrudHavingCommand/{pseudo}")
    public ResponseEntity<Long> totalDrudHavingCommand(@PathVariable String pseudo){
        return new ResponseEntity<>(commandService.totalQteDrugHavingCommand(pseudo),HttpStatus.OK);
    }

    @GetMapping("/commanDay/{userName}")
    public ResponseEntity<List<CommandeResponseDto>> getCommandDayforUser(@PathVariable String userName){
        return new ResponseEntity<>(commandService.getListCommandInThisDay(userName),HttpStatus.OK);
    }
}
