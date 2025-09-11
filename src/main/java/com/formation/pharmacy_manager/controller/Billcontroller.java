package com.formation.pharmacy_manager.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.formation.pharmacy_manager.dto.billDto.BillRequestDto;
import com.formation.pharmacy_manager.dto.billDto.BillResponseDto;
import com.formation.pharmacy_manager.entities.Bill;
import com.formation.pharmacy_manager.services.BillService.BillService;


@RestController
@RequestMapping("/api/bill")
public class Billcontroller {

    @Autowired
    private BillService billService;

    @PostMapping("/create")
    public ResponseEntity<BillResponseDto> createBill(@RequestBody BillRequestDto billRequest) {
        try {
            Bill bill = billService.generateBill(billRequest.getPaymentId(), billRequest.getPaymentMethod());
            BillResponseDto responseDto = new BillResponseDto(
                bill.getBillId(),
                bill.getPayment().getPaymentId(),
                bill.getCreationDate(),
                bill.getTotalAmount(),
                bill.getPayment().getPaymentMethod());
            
            return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Retourne une réponse avec le statut 400 et le message d'erreur de l'exception
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findBillById(@PathVariable long id){
        return ResponseEntity.ok(billService.getBillById(id));
    }
    
}
